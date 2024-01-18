package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.model.Horario;
import pt.ipb.galconverterapi.repository.HorarioRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class HorarioConverter {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> convert(List<LessonDto> lessonDtos) {
        System.out.println("horarios converter. LessonDto.size(): " + lessonDtos.size());
        horarioRepository.deleteAll();
        return new ArrayList<>();
    }
}
