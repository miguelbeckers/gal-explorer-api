package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.oldToNew.*;
import pt.ipb.galconverterapi.repository._new.*;

@Controller
@CrossOrigin
@RequestMapping("/get")
public class GetterController {
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


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello getter!");
    }

    @GetMapping("/subjects")
    public ResponseEntity<Object> getSubjects() {
        return ResponseEntity.ok().body(subjectRepository.findAll());
    }

    @GetMapping("/periods")
    public ResponseEntity<Object> getPeriods() {
        return ResponseEntity.ok().body(periodRepository.findAll());
    }

    @GetMapping("/resources")
    public ResponseEntity<Object> getResources() {
        return ResponseEntity.ok().body(resourceRepository.findAll());
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> getDepartments() {
        return ResponseEntity.ok().body(departmentRepository.findAll());
    }

    @GetMapping("/subjectTypes")
    public ResponseEntity<Object> getSubjectTypes() {
        return ResponseEntity.ok().body(subjectTypeRepository.findAll());
    }

    @GetMapping("/timeslots")
    public ResponseEntity<Object> getTimeslots() {
        return ResponseEntity.ok().body(timeslotRepository.findAll());
    }

    @GetMapping("/professors")
    public ResponseEntity<Object> getProfessors() {
        return ResponseEntity.ok().body(professorRepository.findAll());
    }

    @GetMapping("/professors/{id}")
    public ResponseEntity<Object> getProfessorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(professorRepository.findById(id));
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getCourses() {
        return ResponseEntity.ok().body(courseRepository.findAll());
    }

    @GetMapping("/classroom-resources")
    public ResponseEntity<Object> getClassroomResources() {
        return ResponseEntity.ok().body(classroomResourceRepository.findAll());
    }

    @GetMapping("/classrooms")
    public ResponseEntity<Object> getClassrooms() {
        return ResponseEntity.ok().body(classroomRepository.findAll());
    }

    @GetMapping("/lesson-resources")
    public ResponseEntity<Object> getLessonResources() {
        return ResponseEntity.ok().body(lessonResourceRepository.findAll());
    }

    @GetMapping("/subject-courses")
    public ResponseEntity<Object> getSubjectCourses() {
        return ResponseEntity.ok().body(subjectCourseRepository.findAll());
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getStudents() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(studentRepository.findById(id));
    }

    @GetMapping("/lesson-students")
    public ResponseEntity<Object> getLessonStudents() {
        return ResponseEntity.ok().body(lessonStudentRepository.findAll());
    }

    @GetMapping("/lessons")
    public ResponseEntity<Object> getLessons() {
        return ResponseEntity.ok().body(lessonRepository.findAll());
    }
}
