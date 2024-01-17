package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Course;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Curso;
import pt.ipb.galconverterapi.model.old.Disciplina;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.model.old.Indisponibilidade;
import pt.ipb.galconverterapi.repository._new.DepartmentRepository;
import pt.ipb.galconverterapi.repository._new.TimeslotRepository;
import pt.ipb.galconverterapi.repository.old.CursoRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CourseConverter {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    public List<Course> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<Course> courses = new ArrayList<>();

        List<Indisponibilidade> indisponibilidades = indisponibilidadeRepository.findAll();
        List<Timeslot> timeslots = timeslotRepository.findAll();

        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();

        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        HashMap<Integer, Disciplina> disciplinaHashMap = new HashMap<>();
        for (Disciplina disciplina : disciplinas) {
            disciplinaHashMap.put(disciplina.getId(), disciplina);
        }

        List<Department> departments = departmentRepository.findAll();
        HashMap<Long, Department> departmentHashMap = new HashMap<>();
        for (Department department : departments) {
            departmentHashMap.put(department.getId(), department);
        }

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

            DisciplinaCurso disciplinaCurso = disciplinaCursos.stream()
                    .filter(dc -> dc.getIdCurso() == curso.getId())
                    .findFirst()
                    .orElse(null);

            if (disciplinaCurso != null) {
                Disciplina disciplina = disciplinaHashMap.get(disciplinaCurso.getIdDiscip());
                course.setDepartment(departmentHashMap.get((long) disciplina.getIdDepart()));
            } else {
                course.setDepartment(null);
            }

            List<Indisponibilidade> indisponibilidadesProfessor = indisponibilidades.stream()
                    .filter(indisponibilidade -> indisponibilidade.getIdTipo() == curso.getId()
                            && indisponibilidade.getTipo().equals("C"))
                    .toList();

            //TODO: implement UnavailabilityConverter
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
