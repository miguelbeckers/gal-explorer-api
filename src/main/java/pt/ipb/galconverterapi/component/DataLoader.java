package pt.ipb.galconverterapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.converter.*;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private SubjectConverter subjectConverter;

    @Autowired
    private PeriodConverter periodConverter;

    @Autowired
    private ResourceConverter resourceConverter;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private SubjectTypeConverter subjectTypeConverter;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @Autowired
    private ProfessorConverter professorConverter;

    @Autowired
    private CourseConverter courseConverter;

    @Autowired
    private ClassroomResourceConverter classroomResourceConverter;

    @Autowired
    private ClassroomConverter classroomConverter;

    @Autowired
    private LessonResourceConverter lessonResourceConverter;

    @Autowired
    private SubjectCourseConverter subjectCourseConverter;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private LessonStudentConverter lessonStudentConverter;

    @Autowired
    private LessonConverter lessonConverter;

    @Override
    public void run(ApplicationArguments args) {
        load();
    }

    public void load() {
        try {
            subjectConverter.convert();
            periodConverter.convert();
            resourceConverter.convert();
            departmentConverter.convert();
            subjectTypeConverter.convert();
            timeslotConverter.convert();
            professorConverter.convert();
            courseConverter.convert();
            classroomResourceConverter.convert();
            classroomConverter.convert();
            lessonResourceConverter.convert();
            subjectCourseConverter.convert();
            studentConverter.convert();
            lessonStudentConverter.convert();
            lessonConverter.convert();

            System.out.println("Data successfully converted!");
        } catch (Exception e) {
            System.out.println("Error converting data");
            System.out.println(e.getMessage());
        }
    }
}
