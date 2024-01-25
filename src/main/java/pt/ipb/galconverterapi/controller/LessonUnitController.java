package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.LessonUnitMapper;

@Controller
@CrossOrigin
@RequestMapping("/lesson-units")
public class LessonUnitController {
    private final LessonUnitMapper lessonUnitMapper;

    @Autowired
    public LessonUnitController(LessonUnitMapper lessonUnitMapper) {
        this.lessonUnitMapper = lessonUnitMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getLessonUnits() {
        return ResponseEntity.ok().body(lessonUnitMapper.convert());
    }
}
