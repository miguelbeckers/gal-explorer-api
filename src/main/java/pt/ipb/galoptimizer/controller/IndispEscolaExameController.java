package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/indisp-escola-exame")
public class IndispEscolaExameController {
    @GetMapping("/hello")
    public String hello() {
        return "hello indisp-escola-exame";
    }
}
