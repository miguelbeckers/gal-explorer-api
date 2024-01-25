package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Horario;
import pt.ipb.galconverterapi.repository.HorarioRepository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Component
public class HorarioMapper {
    private final HorarioRepository horarioRepository;
    private final TimeslotMapper timeslotMapper;

    @Autowired
    public HorarioMapper(HorarioRepository horarioRepository,
                         TimeslotMapper timeslotMapper) {
        this.horarioRepository = horarioRepository;
        this.timeslotMapper = timeslotMapper;
    }

    public List<Horario> convert(List<LessonUnitDto> lessonUntiDtos) {
        List<TimeslotDto> timeslotDtos = timeslotMapper.convert();

        HashMap<Long, TimeslotDto> timeslotDtoHashMap = new HashMap<>();
        for (TimeslotDto timeslotDto : timeslotDtos) {
            timeslotDtoHashMap.put(timeslotDto.getId(), timeslotDto);
        }

        List<Horario> horarios = new ArrayList<>();
        int id = 1;

        for (LessonUnitDto lessonDto : lessonUntiDtos) {
            if (lessonDto.getTimeslotId() != null && lessonDto.getLessonId() != null) {
                TimeslotDto timeslotDto = timeslotDtoHashMap.get(lessonDto.getTimeslotId());

                Horario horario = new Horario();
                horario.setId(id++);
                horario.setIdSala(lessonDto.getClassroomId().intValue());
                horario.setInicio(Time.valueOf(timeslotDto.getStartTime()));
                horario.setFim(Time.valueOf(timeslotDto.getEndTime()));
                horario.setIdDia(timeslotDto.getDayOfWeek().getValue());
                horario.setIdAula(lessonDto.getLessonId().intValue());
                horarios.add(horario);
            }
        }

        horarios.sort(Comparator.comparing(Horario::getIdDia)
                .thenComparing(Horario::getIdSala)
                .thenComparing(Horario::getIdAula)
                .thenComparing(Horario::getInicio));

        int i = 0;

        while (i < horarios.size() - 1) {
            Horario horario = horarios.get(i);
            Horario nextHorario = horarios.get(i + 1);

            if (horario.getIdAula() == nextHorario.getIdAula()
                    && horario.getIdDia() == nextHorario.getIdDia()
                    && horario.getIdSala() == nextHorario.getIdSala()) {
                horarios.remove(i + 1);
                horario.setFim(nextHorario.getFim());
            } else {
                i++;
            }
        }

        horarioRepository.deleteAll();
        return horarioRepository.saveAll(horarios);
    }
}
