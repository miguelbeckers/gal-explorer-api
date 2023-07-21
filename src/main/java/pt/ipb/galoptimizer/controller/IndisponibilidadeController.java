package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/indisponibilidade")
//TODO: Esta entidade não pode ser persistida pois não possúi um id
public class IndisponibilidadeController {
    @GetMapping("/hello")
    public String hello() {
        return "hello indisponibilidade";
    }
}
