package pt.ipb.galoptimizer.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.data.service.DisciplinaCursoService;

@Controller
@RequestMapping("/disciplina-curso")
public class DisciplinaCursoController {
    @Autowired
    private DisciplinaCursoService disciplinaCursoService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello disciplina-curso");
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(disciplinaCursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(disciplinaCursoService.findById(id));
    }
}
