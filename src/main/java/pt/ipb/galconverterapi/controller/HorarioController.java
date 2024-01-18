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
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.model.Horario;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    private HorarioConverter horarioConverter;
    @PostMapping
    public ResponseEntity<Object> postLessons(@RequestBody List<LessonDto> lessonDtos) {
        List<Horario> horarios = horarioConverter.convert(lessonDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarios);
    }
}
