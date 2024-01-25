package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.SubjectCourseMapper;

@Controller
@CrossOrigin
@RequestMapping("/subject-courses")
public class SubjectCourseController {
    private final SubjectCourseMapper subjectCourseMapper;

    @Autowired
    public SubjectCourseController(SubjectCourseMapper subjectCourseMapper) {
        this.subjectCourseMapper = subjectCourseMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getSubjectCourses() {
        return ResponseEntity.ok().body(subjectCourseMapper.convert());
    }
}
