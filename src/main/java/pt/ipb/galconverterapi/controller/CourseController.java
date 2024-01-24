package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.CourseConverter;

@Controller
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {
    private final CourseConverter courseConverter;

    @Autowired
    public CourseController(CourseConverter courseConverter) {
        this.courseConverter = courseConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getCourses() {
        return ResponseEntity.ok().body(courseConverter.convert());
    }
}
