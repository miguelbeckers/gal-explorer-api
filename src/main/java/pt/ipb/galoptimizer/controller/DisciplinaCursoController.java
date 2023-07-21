package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disciplina-curso")
public class DisciplinaCursoController {
    @GetMapping("/hello")
    public String hello() {
        return "hello disciplina-curso";
    }
}
