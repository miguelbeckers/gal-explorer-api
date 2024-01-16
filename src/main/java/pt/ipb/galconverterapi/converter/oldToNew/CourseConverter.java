package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Course;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Curso;
import pt.ipb.galconverterapi.model.old.Indisponibilidade;
import pt.ipb.galconverterapi.repository._new.DepartmentRepository;
import pt.ipb.galconverterapi.repository._new.TimeslotRepository;
import pt.ipb.galconverterapi.repository.old.CursoRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseConverter {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    public List<Course> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<Course> courses = new ArrayList<>();

        List<Department> departments = departmentRepository.findAll();
        List<Indisponibilidade> indisponibilidades = indisponibilidadeRepository.findAll();
        List<Timeslot> timeslots = timeslotRepository.findAll();

        for (Curso curso : cursos) {
            Course course = new Course();
            course.setId((long) curso.getId());
            course.setName(curso.getNome());
            course.setAbbreviation(curso.getAbrev());

            course.setIpbCodEscola(curso.getIpbCodEscola());
            course.setIpbCodCurso(curso.getIpbCodCurso());
            course.setIpbNPlano(curso.getIpbNPlano());
            course.setInicioAulas(curso.getInicioAulas());
            course.setFimAulas(curso.getFimAulas());

            //TODO: identify how the departments are related to the courses

            List<Indisponibilidade> indisponibilidadesProfessor = indisponibilidades.stream()
                    .filter(indisponibilidade -> indisponibilidade.getIdTipo() == curso.getId()
                            && indisponibilidade.getTipo().equals("C"))
                    .toList();

            List<Timeslot> courseUnavailability = new ArrayList<>();
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

                courseUnavailability.add(timeslot);
            }

            course.setUnavailability(courseUnavailability);
            courses.add(course);
        }

        return courses;
    }
}
