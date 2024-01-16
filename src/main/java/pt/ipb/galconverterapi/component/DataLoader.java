package pt.ipb.galconverterapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.repository._new.*;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SubjectTypeRepository subjectTypeRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassroomResourceRepository classroomResourceRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private LessonResourceRepository lessonResourceRepository;

    @Autowired
    private SubjectCourseRepository subjectCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonStudentRepository lessonStudentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        load();
    }

    public void load() {
        try {
            subjectRepository.load();
            periodRepository.load();
            resourceRepository.load();
            departmentRepository.load();
            subjectTypeRepository.load();
            timeslotRepository.load();
            professorRepository.load();
            courseRepository.load();
            classroomResourceRepository.load();
            classroomRepository.load();
            lessonResourceRepository.load();
            subjectCourseRepository.load();
            studentRepository.load();
            lessonStudentRepository.load();
            lessonRepository.load();

            System.out.println("Data successfully loaded!");
        } catch (Exception e) {
            System.out.println("Error loading data");
            System.out.println(e.getMessage());
        }
    }
}
