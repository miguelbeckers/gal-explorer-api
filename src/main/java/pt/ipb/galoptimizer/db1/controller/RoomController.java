package pt.ipb.galoptimizer.db1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ipb.galoptimizer.db1.service.RoomService;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello aluno-disciplina");
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

}
