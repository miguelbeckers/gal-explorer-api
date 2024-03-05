package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.ClassroomResourceMapper;

@Controller
@CrossOrigin
@RequestMapping("/classroom-resources")
public class ClassroomResourceController {
    private final ClassroomResourceMapper classroomResourceMapper;

    @Autowired
    public ClassroomResourceController(ClassroomResourceMapper classroomResourceMapper) {
        this.classroomResourceMapper = classroomResourceMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getClassroomResources() {
        return ResponseEntity.ok().body(classroomResourceMapper.map());
    }
}
