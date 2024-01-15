package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.DepartmentDto;
import pt.ipb.galconverterapi.dto.ProfessorDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Docente;
import pt.ipb.galconverterapi.repository.DocenteRepository;
import pt.ipb.galconverterapi.repository.HorarioRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProfessorDtoConverter {
//    @Autowired
//    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private TimeslotDtoConverter timeslotDtoConverter;

    @Autowired
    private DepartmentDtoConverter departmentConverter;

    public List<ProfessorDto> convert() {
        //FIXME: change horarioRepository to indisponibilidadeRepository
        List<Object[]> indisponibilidades = horarioRepository.findIndisponibilidades();

        List<TimeslotDto> timeslots = timeslotDtoConverter.convert();
        List<DepartmentDto> departments = departmentConverter.convert();

        List<Docente> docentes = docenteRepository.findAll();
        List<ProfessorDto> professors = new ArrayList<>();

        for (Docente docente : docentes) {
            ProfessorDto professor = new ProfessorDto();
            professor.setId((long) docente.getId());
            professor.setName(docente.getNome());
            professor.setAbbreviation(docente.getAbrev());

            professor.setEti(docente.getEti());
            professor.setMail(docente.getMail());
            professor.setCredito(docente.getCredito());
            professor.setIpbCodEscola(docente.getIpbCodEscola());
            professor.setIpbEmpNum(docente.getIpbEmpNum());

            Optional<DepartmentDto> optionalDepartment = departments.stream()
                    .filter(department -> department.getId().equals((long) docente.getIdDepart()))
                    .findFirst();

            professor.setDepartmentDto(optionalDepartment.orElseThrow(
                    () -> new RuntimeException("Professor [" + professor.getId() + "] department not found")
            ));

            //FIXME: filter not just by id but by type too
            List<Object[]> indisponibilidadesProfessor = indisponibilidades.stream()
                    .filter(indisponibilidade -> (int) indisponibilidade[1] == docente.getId())
                    .toList();

            List<TimeslotDto> professorUnavailability = new ArrayList<>();
            for (Object[] indisponibilidade : indisponibilidadesProfessor) {
                professorUnavailability.add(timeslots.stream()
                        .filter(timeslot ->
                                timeslot.getDayOfWeek().getValue() == (int) indisponibilidade[3]
                                        && timeslot.getStartTime().equals(indisponibilidade[4])
                                        && timeslot.getEndTime().equals(indisponibilidade[5]))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Timeslot (" +
                                "day[" + DayOfWeek.of((int) indisponibilidade[3]) + "]," +
                                "startTime[" + indisponibilidade[4] + "]," +
                                "startTime[" + indisponibilidade[5] + "]," +
                                "not found"))
                );
            }

            professor.setUnavailability(professorUnavailability);
            professors.add(professor);
        }

        return professors;
    }
}
