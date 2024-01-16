package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Course;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model.old.Curso;
import pt.ipb.galconverterapi.repository.old.CursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseConverter {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DepartmentConverter departmentConverter;

    public List<Course> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<Course> courses = new ArrayList<>();

        List<Department> departments = departmentConverter.convert();

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
//            courseDto.setDepartmentDto(departments.stream()
//                    .filter(department -> department.getId().equals((long) curso.get))
//                    .findFirst()
//                    .orElseThrow(
//                            () -> new RuntimeException("Professor [" + professor.getId() + "] department not found"))
//            );

            //TODO: insert unavailability

            courses.add(course);
        }

        return courses;
    }
}
