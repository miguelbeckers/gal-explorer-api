package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.LessonStudentConverter;

@Controller
@CrossOrigin
@RequestMapping("/lesson-students")
public class LessonStudentController {
    @Autowired
    private LessonStudentConverter lessonStudentConverter;

    @GetMapping
    public ResponseEntity<Object> getLessonStudents() {
        return ResponseEntity.ok().body(lessonStudentConverter.convert());
    }
}
