package pt.ipb.galconverterapi.converter.oldToNew;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Subject;
import pt.ipb.galconverterapi.model._new.SubjectCourse;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectCourseConverter {
    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private SubjectConverter subjectConverter;

    public List<SubjectCourse> convert() {
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<SubjectCourse> subjectCourses = new ArrayList<>();

        List<Subject> subjects = subjectConverter.convert();

        for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
            SubjectCourse subjectCourse = new SubjectCourse();
            subjectCourse.setId((long) disciplinaCurso.getId());

            subjectCourse.setIdCurso(disciplinaCurso.getIdCurso());
            subjectCourse.setIdAno(disciplinaCurso.getIdAno());
            subjectCourse.setNumAlunos(disciplinaCurso.getNumAlunos());

            subjectCourse.setSubject(subjects.stream()
                    .filter(subject -> subject.getId() == disciplinaCurso.getIdDiscip())
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Subject not found for id: " + disciplinaCurso.getIdDiscip())
                    ));

            subjectCourses.add(subjectCourse);
        }

        return subjectCourses;
    }
}
