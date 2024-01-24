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
    private final DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    public SubjectCourseConverter(DisciplinaCursoRepository disciplinaCursoRepository) {
        this.disciplinaCursoRepository = disciplinaCursoRepository;
    }

    public List<SubjectCourseDto> convert() {
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<SubjectCourseDto> subjectCoursDtos = new ArrayList<>();

        for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
            SubjectCourseDto subjectCourseDto = new SubjectCourseDto();
            subjectCourseDto.setId((long) disciplinaCurso.getId());
            subjectCourseDto.setCourseId((long) disciplinaCurso.getIdCurso());
            subjectCourseDto.setSubjectId((long) disciplinaCurso.getIdDiscip());
            subjectCourseDto.setPeriodId((long) disciplinaCurso.getIdAno());
            subjectCoursDtos.add(subjectCourseDto);
        }

        return subjectCoursDtos;
    }
}
