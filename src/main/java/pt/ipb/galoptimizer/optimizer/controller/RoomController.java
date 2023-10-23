package pt.ipb.galoptimizer.optimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ipb.galoptimizer.optimizer.service.RoomService;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello aluno-disciplina");
    }
}
