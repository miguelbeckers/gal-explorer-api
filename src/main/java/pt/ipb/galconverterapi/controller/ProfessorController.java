package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.ProfessorMapper;

@Controller
@CrossOrigin
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorMapper professorMapper;

    @Autowired
    public ProfessorController(ProfessorMapper professorMapper) {
        this.professorMapper = professorMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getProfessors() {
        return ResponseEntity.ok().body(professorMapper.convert());
    }
}
