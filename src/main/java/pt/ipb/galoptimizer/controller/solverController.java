package pt.ipb.galoptimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.model.Horario;
import pt.ipb.galoptimizer.model.Tempo;
import pt.ipb.galoptimizer.repository.HorarioRepository;
import pt.ipb.galoptimizer.repository.TempoRepository;

import java.util.List;

@Controller
@RequestMapping("/solver")
public class solverController {
    @Autowired
    private HorarioRepository horarioRepository;
    @Autowired
    private TempoRepository tempoRepository;

    @GetMapping("/horario")
    public ResponseEntity<Object> horario() {
        List<Horario> horarios = horarioRepository.findAll();
        return ResponseEntity.ok().body(horarios);
    }

    @GetMapping("/tempo")
    public ResponseEntity<Object> tempo() {
        List<Tempo> tempos = tempoRepository.findAll();
        return ResponseEntity.ok().body(tempos);
    }
}
