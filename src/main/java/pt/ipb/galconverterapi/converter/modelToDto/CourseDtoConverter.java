package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.CourseDto;
import pt.ipb.galconverterapi.dto.DepartmentDto;
import pt.ipb.galconverterapi.model.Curso;
import pt.ipb.galconverterapi.repository.CursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDtoConverter {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DepartmentDtoConverter departmentDtoConverter;

    public List<CourseDto> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();

        List<DepartmentDto> departments = departmentDtoConverter.convert();

        for (Curso curso : cursos) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId((long) curso.getId());
            courseDto.setName(curso.getNome());
            courseDto.setAbbreviation(curso.getAbrev());

            courseDto.setIpbCodEscola(curso.getIpbCodEscola());
            courseDto.setIpbCodCurso(curso.getIpbCodCurso());
            courseDto.setIpbNPlano(curso.getIpbNPlano());
            courseDto.setInicioAulas(curso.getInicioAulas());
            courseDto.setFimAulas(curso.getFimAulas());

            //TODO: identify how the departments are related to the courses
//            courseDto.setDepartmentDto(departments.stream()
//                    .filter(department -> department.getId().equals((long) curso.get))
//                    .findFirst()
//                    .orElseThrow(
//                            () -> new RuntimeException("Professor [" + professor.getId() + "] department not found"))
//            );

            //TODO: insert unavailability

            courseDtos.add(courseDto);
        }

        return courseDtos;
    }
}
