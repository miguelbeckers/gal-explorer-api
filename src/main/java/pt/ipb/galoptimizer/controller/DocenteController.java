package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docente")
public class DocenteController {
    @GetMapping("/hello")
    public String hello() {
        return "hello docente";
    }
}
