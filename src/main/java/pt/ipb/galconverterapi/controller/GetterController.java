package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.*;

@Controller
@CrossOrigin
@RequestMapping("/get")
public class GetterController {
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


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello getter!");
    }

    @GetMapping("/subjects")
    public ResponseEntity<Object> getSubjects() {
        return ResponseEntity.ok().body(subjectConverter.convert());
    }

    @GetMapping("/periods")
    public ResponseEntity<Object> getPeriods() {
        return ResponseEntity.ok().body(periodConverter.convert());
    }

    @GetMapping("/resources")
    public ResponseEntity<Object> getResources() {
        return ResponseEntity.ok().body(resourceConverter.convert());
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> getDepartments() {
        return ResponseEntity.ok().body(departmentConverter.convert());
    }

    @GetMapping("/subjectTypes")
    public ResponseEntity<Object> getSubjectTypes() {
        return ResponseEntity.ok().body(subjectTypeConverter.convert());
    }

    @GetMapping("/timeslots")
    public ResponseEntity<Object> getTimeslots() {
        return ResponseEntity.ok().body(timeslotConverter.convert());
    }

    @GetMapping("/professors")
    public ResponseEntity<Object> getProfessors() {
        return ResponseEntity.ok().body(professorConverter.convert());
    }

//    @GetMapping("/professors/{id}")
//    public ResponseEntity<Object> getProfessorById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok().body(professorConverter.findById(id));
//    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getCourses() {
        return ResponseEntity.ok().body(courseConverter.convert());
    }

    @GetMapping("/classroom-resources")
    public ResponseEntity<Object> getClassroomResources() {
        return ResponseEntity.ok().body(classroomResourceConverter.convert());
    }

    @GetMapping("/classrooms")
    public ResponseEntity<Object> getClassrooms() {
        return ResponseEntity.ok().body(classroomConverter.convert());
    }

    @GetMapping("/lesson-resources")
    public ResponseEntity<Object> getLessonResources() {
        return ResponseEntity.ok().body(lessonResourceConverter.convert());
    }

    @GetMapping("/subject-courses")
    public ResponseEntity<Object> getSubjectCourses() {
        return ResponseEntity.ok().body(subjectCourseConverter.convert());
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getStudents() {
        return ResponseEntity.ok().body(studentConverter.convert());
    }

//    @GetMapping("/students/{id}")
//    public ResponseEntity<Object> getStudentById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok().body(studentConverter.findById(id));
//    }

    @GetMapping("/lesson-students")
    public ResponseEntity<Object> getLessonStudents() {
        return ResponseEntity.ok().body(lessonStudentConverter.convert());
    }

    @GetMapping("/lessons")
    public ResponseEntity<Object> getLessons() {
        return ResponseEntity.ok().body(lessonConverter.convert());
    }
}
