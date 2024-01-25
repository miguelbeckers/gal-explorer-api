package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.HorarioMapper;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.model.Horario;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/horarios")
public class HorarioController {
    private final HorarioMapper horarioMapper;

    @Autowired
    public HorarioController(HorarioMapper horarioMapper) {
        this.horarioMapper = horarioMapper;
    }

    @PostMapping
    public ResponseEntity<Object> postLessonUnits(@RequestBody List<LessonUnitDto> lessonUnitDtos) {
        List<Horario> horarios = horarioMapper.convert(lessonUnitDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarios);
    }
}
