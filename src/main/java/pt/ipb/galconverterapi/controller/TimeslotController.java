package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.TimeslotMapper;

@Controller
@CrossOrigin
@RequestMapping("/timeslots")
public class TimeslotController {
    private final TimeslotMapper timeslotMapper;

    @Autowired
    public TimeslotController(TimeslotMapper timeslotMapper) {
        this.timeslotMapper = timeslotMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getTimeslots() {
        return ResponseEntity.ok().body(timeslotMapper.convert());
    }
}
