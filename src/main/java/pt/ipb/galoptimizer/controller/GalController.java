package pt.ipb.galoptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gal")
//TODO: Não pode ser entidade porque não tem id
public class GalController {
    @GetMapping("/hello")
    public String hello() {
        return "hello gal";
    }
}
