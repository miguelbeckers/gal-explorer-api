package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.PeriodConverter;

@Controller
@CrossOrigin
@RequestMapping("/periods")
public class PeriodController {
    @Autowired
    private PeriodConverter periodConverter;

    @GetMapping
    public ResponseEntity<Object> getPeriods() {
        return ResponseEntity.ok().body(periodConverter.convert());
    }
}
