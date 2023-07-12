package pt.ipb.galoptimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.model.Horario;
import pt.ipb.galoptimizer.repository.HorarioRepository;

import java.util.List;

@Controller
@RequestMapping("/classrooms")
public class testController {
    @Autowired
    private HorarioRepository horarioRepository;
    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        List<Horario> horarios = horarioRepository.findAll();
        return ResponseEntity.ok().body(horarios);
    }
}
