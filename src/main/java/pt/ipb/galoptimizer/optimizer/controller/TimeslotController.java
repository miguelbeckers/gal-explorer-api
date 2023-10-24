package pt.ipb.galoptimizer.optimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.optimizer.service.TimeslotService;

@Controller
@CrossOrigin
@RequestMapping("/timeslots")
public class TimeslotController {
    @Autowired
    private TimeslotService timeslotService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello timeslots!");
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(timeslotService.findAll());
    }

}
