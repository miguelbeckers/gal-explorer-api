package pt.ipb.galoptimizer.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.data.service.MarcacaoExameService;

@Controller
@RequestMapping("/marcacao-exame")
public class MarcacaoExameController {
    @Autowired
    private MarcacaoExameService marcacaoExameService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello marcacao-exame");
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(marcacaoExameService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(marcacaoExameService.findById(id));
    }
}
