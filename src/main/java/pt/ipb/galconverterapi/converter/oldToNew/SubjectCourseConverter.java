package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.SubjectCourse;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectCourseConverter {
    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    public List<SubjectCourse> convert() {
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<SubjectCourse> subjectCourses = new ArrayList<>();

        for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
            SubjectCourse subjectCourse = new SubjectCourse();
            subjectCourse.setId((long) disciplinaCurso.getId());
            subjectCourse.setCourse((long) disciplinaCurso.getIdCurso());
            subjectCourse.setSubject((long) disciplinaCurso.getIdDiscip());
            subjectCourse.setPeriod((long) disciplinaCurso.getIdAno());
            subjectCourses.add(subjectCourse);
        }

        return subjectCourses;
    }
}
