package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.LessonConverter;

@Controller
@CrossOrigin
@RequestMapping("/lessons")
public class LessonController {
    private final LessonConverter lessonConverter;

    @Autowired
    public LessonController(LessonConverter lessonConverter) {
        this.lessonConverter = lessonConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getLessons() {
        return ResponseEntity.ok().body(lessonConverter.convert());
    }
}
