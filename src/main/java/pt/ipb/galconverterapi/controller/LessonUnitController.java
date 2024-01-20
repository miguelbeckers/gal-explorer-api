package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.LessonUnitConverter;

@Controller
@CrossOrigin
@RequestMapping("/lessons-units")
public class LessonUnitController {
    @Autowired
    private LessonUnitConverter lessonUnitConverter;

    @GetMapping
    public ResponseEntity<Object> getLessonUnits() {
        return ResponseEntity.ok().body(lessonUnitConverter.convert());
    }
}
