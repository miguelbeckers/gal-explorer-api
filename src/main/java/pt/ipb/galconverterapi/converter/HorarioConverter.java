package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Horario;
import pt.ipb.galconverterapi.repository.HorarioRepository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class HorarioConverter {
    private final HorarioRepository horarioRepository;
    private final TimeslotConverter timeslotConverter;

    @Autowired
    public HorarioConverter(HorarioRepository horarioRepository,
                            TimeslotConverter timeslotConverter) {
        this.horarioRepository = horarioRepository;
        this.timeslotConverter = timeslotConverter;
    }

    public List<Horario> convert(List<LessonUnitDto> lessonUntiDtos) {
        List<TimeslotDto> timeslotDtos = timeslotConverter.getIsConverted()
                ? timeslotConverter.getTimeslotDtos() : timeslotConverter.convert();

        HashMap<Long, TimeslotDto> timeslotDtoHashMap = new HashMap<>();
        for (TimeslotDto timeslotDto : timeslotDtos) {
            timeslotDtoHashMap.put(timeslotDto.getId(), timeslotDto);
        }

        List<Horario> horarios = new ArrayList<>();

        for (LessonUnitDto lessonDto : lessonUntiDtos) {
            if (lessonDto.getTimeslotId() != null && lessonDto.getLessonId() != null) {
                TimeslotDto timeslotDto = timeslotDtoHashMap.get(lessonDto.getTimeslotId());

                Horario horario = new Horario();
                horario.setIdSala(lessonDto.getClassroomId().intValue());
                horario.setInicio(Time.valueOf(timeslotDto.getStartTime()));
                horario.setFim(Time.valueOf(timeslotDto.getEndTime()));
                horario.setIdDia(timeslotDto.getDayOfWeek().getValue());
                horario.setIdAula(lessonDto.getLessonId().intValue());
                horarios.add(horario);
            }
        }

        horarioRepository.deleteAll();
        return horarioRepository.saveAll(horarios);
    }
}
