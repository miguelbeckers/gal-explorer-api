package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.SubjectCourseDto;
import pt.ipb.galconverterapi.model.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectCourseConverter {
    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    public List<SubjectCourseDto> convert() {
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<SubjectCourseDto> subjectCoursDtos = new ArrayList<>();

        for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
            SubjectCourseDto subjectCourseDto = new SubjectCourseDto();
            subjectCourseDto.setId((long) disciplinaCurso.getId());
            subjectCourseDto.setCourse((long) disciplinaCurso.getIdCurso());
            subjectCourseDto.setSubject((long) disciplinaCurso.getIdDiscip());
            subjectCourseDto.setPeriod((long) disciplinaCurso.getIdAno());
            subjectCoursDtos.add(subjectCourseDto);
        }

        return subjectCoursDtos;
    }
}
