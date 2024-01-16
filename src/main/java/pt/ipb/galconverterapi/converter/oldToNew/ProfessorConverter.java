package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Professor;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Docente;
import pt.ipb.galconverterapi.repository.old.DocenteRepository;
import pt.ipb.galconverterapi.repository.old.HorarioRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorConverter {
//    @Autowired
//    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @Autowired
    private DepartmentConverter departmentConverter;

    public List<Professor> convert() {
        //FIXME: change horarioRepository to indisponibilidadeRepository
        List<Object[]> indisponibilidades = horarioRepository.findIndisponibilidades();

        List<Timeslot> timeslots = timeslotConverter.convert();
        List<Department> departments = departmentConverter.convert();

        List<Docente> docentes = docenteRepository.findAll();
        List<Professor> professors = new ArrayList<>();

        for (Docente docente : docentes) {
            Professor professor = new Professor();
            professor.setId((long) docente.getId());
            professor.setName(docente.getNome());
            professor.setAbbreviation(docente.getAbrev());

            professor.setEti(docente.getEti());
            professor.setMail(docente.getMail());
            professor.setCredito(docente.getCredito());
            professor.setIpbCodEscola(docente.getIpbCodEscola());
            professor.setIpbEmpNum(docente.getIpbEmpNum());

            professor.setDepartment(departments.stream()
                    .filter(department -> department.getId().equals((long) docente.getIdDepart()))
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Professor [" + professor.getId() + "] department not found"))
            );

            //FIXME: filter not just by id but by type too
            List<Object[]> indisponibilidadesProfessor = indisponibilidades.stream()
                    .filter(indisponibilidade -> (int) indisponibilidade[1] == docente.getId())
                    .toList();

            List<Timeslot> professorUnavailability = new ArrayList<>();
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
