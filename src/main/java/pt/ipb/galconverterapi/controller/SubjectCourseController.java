package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.SubjectCourseConverter;

@Controller
@CrossOrigin
@RequestMapping("/subject-courses")
public class SubjectCourseController {
    @Autowired
    private SubjectCourseConverter subjectCourseConverter;

    @GetMapping
    public ResponseEntity<Object> getSubjectCourses() {
        return ResponseEntity.ok().body(subjectCourseConverter.convert());
    }
}
