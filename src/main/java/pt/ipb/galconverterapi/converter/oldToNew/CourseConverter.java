package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Course;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Curso;
import pt.ipb.galconverterapi.model.old.Disciplina;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository._new.DepartmentRepository;
import pt.ipb.galconverterapi.repository.old.CursoRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

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
    private DepartmentRepository departmentRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    public List<Course> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<Course> courses = new ArrayList<>();

        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();
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

            DisciplinaCurso disciplinaCurso = disciplinaCursos.stream()
                    .filter(dc -> dc.getIdCurso() == curso.getId())
                    .findFirst()
                    .orElse(null);

            if (disciplinaCurso != null) {
                Disciplina disciplina = disciplinaHashMap.get(disciplinaCurso.getIdDiscip());
                course.setDepartment(departmentHashMap.get((long) disciplina.getIdDepart()).getId());
            } else {
                course.setDepartment(null);
            }

            List<Object[]> indisponibilidadesCurso = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("C") && idTipo == curso.getId()) {
                    indisponibilidadesCurso.add(indisponibilidade);
                }
            }

            List<Timeslot> courseUnavailability = timeslotConverter.convert(indisponibilidadesCurso);

            course.setUnavailability(courseUnavailability.stream()
                    .map(Timeslot::getId)
                    .toList());

            courses.add(course);
        }

        return courses;
    }
}
