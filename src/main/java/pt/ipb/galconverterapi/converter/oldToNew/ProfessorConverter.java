package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Professor;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Docente;
import pt.ipb.galconverterapi.model.old.Indisponibilidade;
import pt.ipb.galconverterapi.repository._new.DepartmentRepository;
import pt.ipb.galconverterapi.repository._new.TimeslotRepository;
import pt.ipb.galconverterapi.repository.old.DocenteRepository;
import pt.ipb.galconverterapi.repository.old.HorarioRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorConverter {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Professor> convert() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Indisponibilidade> indisponibilidades = indisponibilidadeRepository.findAll();

        List<Timeslot> timeslots = timeslotRepository.findAll();
        List<Department> departments = departmentRepository.findAll();

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
                            () -> new RuntimeException("Department [" + docente.getIdDepart() + "] not found")
                    )
            );

            List<Indisponibilidade> indisponibilidadesProfessor = indisponibilidades.stream()
                    .filter(indisponibilidade -> indisponibilidade.getIdTipo() == docente.getId()
                            && indisponibilidade.getTipo().equals("D"))
                    .toList();

            //FIXME: the timeslots are not being applied to all the professors that have unavailability
            List<Timeslot> professorUnavailability = new ArrayList<>();
            for (Indisponibilidade indisponibilidade : indisponibilidadesProfessor) {
                Timeslot timeslot = timeslots.stream()
                        .filter(t -> t.getDayOfWeek().equals(DayOfWeek.of(indisponibilidade.getIdDia())))
                        .filter(t -> t.getStartTime().equals(indisponibilidade.getInicio().toLocalTime()))
                        .filter(t -> t.getEndTime().equals(indisponibilidade.getFim().toLocalTime()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException("Timeslot [" + indisponibilidade.getIdDia() +
                                        ", " + indisponibilidade.getInicio() +
                                        ", " + indisponibilidade.getFim() + "] not found")
                        );

                professorUnavailability.add(timeslot);
            }

            professor.setUnavailability(professorUnavailability);
            professors.add(professor);
        }

        return professors;
    }
}
