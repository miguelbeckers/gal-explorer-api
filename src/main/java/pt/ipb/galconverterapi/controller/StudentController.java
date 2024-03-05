package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.StudentMapper;

@Controller
@CrossOrigin
@RequestMapping("/students")
public class StudentController {
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getStudents() {
        return ResponseEntity.ok().body(studentMapper.map());
    }
}
