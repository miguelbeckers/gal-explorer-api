package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.HorarioConverter;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.model.Horario;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/horarios")
public class HorarioController {
    private final HorarioConverter horarioConverter;

    @Autowired
    public HorarioController(HorarioConverter horarioConverter) {
        this.horarioConverter = horarioConverter;
    }

    @PostMapping
    public ResponseEntity<Object> postLessonUnits(@RequestBody List<LessonUnitDto> lessonUnitDtos) {
        List<Horario> horarios = horarioConverter.convert(lessonUnitDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarios);
    }
}
