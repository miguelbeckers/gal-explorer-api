package pt.ipb.galoptimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.service.EventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello evento");
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(eventoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(eventoService.findById(id));
    }
}
