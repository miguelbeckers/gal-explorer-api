package pt.ipb.galconverterapi.converter;

import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.model.Horario;

import java.util.ArrayList;
import java.util.List;

@Component
public class HorarioConverter {
    public List<Horario> convert(List<LessonDto> lessonDtos) {
        System.out.println("horarios converter. LessonDto.size(): " + lessonDtos.size());
        return new ArrayList<>();
    }
}
