package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.StudentConverter;

@Controller
@CrossOrigin
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentConverter studentConverter;

    @GetMapping
    public ResponseEntity<Object> getStudents() {
        return ResponseEntity.ok().body(studentConverter.convert());
    }
}
