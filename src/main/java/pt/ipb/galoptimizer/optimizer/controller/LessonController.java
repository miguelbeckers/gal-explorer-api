package pt.ipb.galoptimizer.optimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.optimizer.service.LessonService;

@Controller
@CrossOrigin
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello lessons!");
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @PostMapping("/load")
    public ResponseEntity<Object> loadAll() {
        lessonService.loadAll();
        return ResponseEntity.ok().build();
    }
}
