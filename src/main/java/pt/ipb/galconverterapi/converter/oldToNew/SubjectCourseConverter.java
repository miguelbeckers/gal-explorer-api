package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Course;
import pt.ipb.galconverterapi.model._new.Period;
import pt.ipb.galconverterapi.model._new.Subject;
import pt.ipb.galconverterapi.model._new.SubjectCourse;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository._new.CourseRepository;
import pt.ipb.galconverterapi.repository._new.PeriodRepository;
import pt.ipb.galconverterapi.repository._new.SubjectRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectCourseConverter {
    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PeriodRepository periodRepository;

    public List<SubjectCourse> convert() {
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<SubjectCourse> subjectCourses = new ArrayList<>();

        List<Subject> subjects = subjectRepository.findAll();
        List<Course> courses = courseRepository.findAll();
        List<Period> periods = periodRepository.findAll();

        for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
            SubjectCourse subjectCourse = new SubjectCourse();
            subjectCourse.setId((long) disciplinaCurso.getId());

            Course subjectCourseCourse = courses.stream()
                    .filter(course -> course.getId() == disciplinaCurso.getIdCurso())
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Course not found for id: " + disciplinaCurso.getIdCurso())
                    );

            subjectCourse.setCourse(subjectCourse.getId());

            Subject subjectCourseSubject = subjects.stream()
                    .filter(subject -> subject.getId() == disciplinaCurso.getIdDiscip())
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Subject not found for id: " + disciplinaCurso.getIdDiscip())
                    );

            subjectCourse.setSubject(subjectCourseSubject.getId());

            Period subjectCoursePeriod = periods.stream()
                    .filter(period -> period.getId() == disciplinaCurso.getIdAno())
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Period not found for id: " + disciplinaCurso.getIdAno())
                    );

            subjectCourse.setPeriod(subjectCoursePeriod.getId());
            subjectCourses.add(subjectCourse);
        }

        return subjectCourses;
    }
}
