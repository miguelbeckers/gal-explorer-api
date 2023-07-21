package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tipo-aula-disciplina")
public class TipoAulaDisciplinaController {
    @GetMapping("/hello")
    public String hello() {
        return "hello tipo-aula-disciplina";
    }
}
