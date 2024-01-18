package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.LessonResourceConverter;

@Controller
@CrossOrigin
@RequestMapping("/lesson-resources")
public class LessonResourceController {
    @Autowired
    private LessonResourceConverter lessonResourceConverter;

    @GetMapping
    public ResponseEntity<Object> getLessonResources() {
        return ResponseEntity.ok().body(lessonResourceConverter.convert());
    }
}
