package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.ClassroomTypeConverter;

@Controller
@CrossOrigin
@RequestMapping("/classroom-types")
public class ClassroomTypeController {
    private final ClassroomTypeConverter classroomTypeConverter;

    @Autowired
    public ClassroomTypeController(ClassroomTypeConverter classroomTypeConverter) {
        this.classroomTypeConverter = classroomTypeConverter;
    }

    @GetMapping
    public ResponseEntity<Object> getCourses() {
        return ResponseEntity.ok().body(classroomTypeConverter.convert());
    }
}
