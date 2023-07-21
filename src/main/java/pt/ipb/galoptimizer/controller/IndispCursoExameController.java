package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/indisp-curso-exame")
public class IndispCursoExameController {
    @GetMapping("/hello")
    public String hello() {
        return "hello indisp-curso-exame";
    }
}
