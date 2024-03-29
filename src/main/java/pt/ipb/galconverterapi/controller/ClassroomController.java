package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.ClassroomMapper;

@Controller
@CrossOrigin
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomMapper classroomMapper;

    @Autowired
    public ClassroomController(ClassroomMapper classroomMapper) {
        this.classroomMapper = classroomMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getClassrooms() {
        return ResponseEntity.ok().body(classroomMapper.map());
    }
}
