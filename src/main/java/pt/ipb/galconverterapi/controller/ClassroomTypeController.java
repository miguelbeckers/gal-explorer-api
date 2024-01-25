package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.ClassroomTypeMapper;

@Controller
@CrossOrigin
@RequestMapping("/classroom-types")
public class ClassroomTypeController {
    private final ClassroomTypeMapper classroomTypeMapper;

    @Autowired
    public ClassroomTypeController(ClassroomTypeMapper classroomTypeMapper) {
        this.classroomTypeMapper = classroomTypeMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getCourses() {
        return ResponseEntity.ok().body(classroomTypeMapper.convert());
    }
}
