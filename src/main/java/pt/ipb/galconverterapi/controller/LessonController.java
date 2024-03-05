package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.LessonMapper;

@Controller
@CrossOrigin
@RequestMapping("/lessons")
public class LessonController {
    private final LessonMapper lessonMapper;

    @Autowired
    public LessonController(LessonMapper lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getLessons() {
        return ResponseEntity.ok().body(lessonMapper.map());
    }
}
