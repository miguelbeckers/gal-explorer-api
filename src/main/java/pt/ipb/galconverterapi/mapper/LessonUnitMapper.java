package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Horario;
import pt.ipb.galconverterapi.repository.HorarioRepository;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonUnitMapper {
    private final LessonMapper lessonMapper;
    private final TimeslotMapper timeslotMapper;
    private final HorarioRepository horarioRepository;
    private static final int UNIT = 30;
    private static final int HOUR = 60;

    @Autowired
    public LessonUnitMapper(LessonMapper lessonMapper,
                            HorarioRepository horarioRepository,
                            TimeslotMapper timeslotMapper) {
        this.lessonMapper = lessonMapper;
        this.horarioRepository = horarioRepository;
        this.timeslotMapper = timeslotMapper;
    }

    public List<LessonUnitDto> map() {
        return createLessonUnitsWithTimeslotsAndClassroom();
//        return createLessonUnits();
    }

    private List<LessonUnitDto> createLessonUnits() {
        List<LessonDto> lessonDtos = lessonMapper.map();

        List<LessonUnitDto> lessonUnitDtos = new ArrayList<>();
        long id = 1L;

        for (LessonDto lessonDto : lessonDtos) {
            int units = (int) Math.round(lessonDto.getHoursPerWeek() * HOUR / UNIT);

            for (int i = 0; i < units; i++) {
                LessonUnitDto lessonUnitDto = new LessonUnitDto();
                lessonUnitDto.setId(id++);
                lessonUnitDto.setLessonId(lessonDto.getId());
                lessonUnitDto.setBlockSize((double) UNIT / HOUR);
                lessonUnitDtos.add(lessonUnitDto);
            }
        }

        return lessonUnitDtos;
    }

    private List<LessonUnitDto> createLessonUnitsWithTimeslotsAndClassroom() {
        List<Horario> horarios = horarioRepository.findAll();

        List<LessonDto> lessonDtos = lessonMapper.map();
        List<TimeslotDto> timeslotDtos = timeslotMapper.map();

        List<LessonUnitDto> lessonUnitDtos = new ArrayList<>();
        long id = 1L;

        for (LessonDto lessonDto : lessonDtos) {
            long lessonUnits = Math.round(lessonDto.getHoursPerWeek() * HOUR / UNIT);
            List<Horario> horariosDaDisciplina = horarios.stream().filter(h -> h.getIdAula() == lessonDto.getId()).toList();

            for (Horario horario : horariosDaDisciplina) {
                Duration duration = Duration.between(horario.getInicio().toLocalTime(), horario.getFim().toLocalTime());
                int horarioUnits = Math.round((float) duration.toMinutes() / UNIT);

                for (int i = 0; i < horarioUnits; i++) {
                    LocalTime startTime = horario.getInicio().toLocalTime().plus(Duration.ofMinutes((long) i * UNIT));
                    LocalTime endTime = horario.getInicio().toLocalTime().plus(Duration.ofMinutes((long) (i + 1) * UNIT));

                    TimeslotDto timeslotDto = timeslotDtos.stream()
                            .filter(t -> t.getDayOfWeek().equals(DayOfWeek.of(horario.getIdDia()))
                                    && t.getStartTime().equals(startTime)
                                    && t.getEndTime().equals(endTime))
                            .findFirst()
                            .orElse(null);

                    LessonUnitDto lessonUnitDto = new LessonUnitDto();
                    lessonUnitDto.setId(id++);
                    lessonUnitDto.setLessonId(lessonDto.getId());
                    assert timeslotDto != null;
                    lessonUnitDto.setTimeslotId(timeslotDto.getId());
                    lessonUnitDto.setClassroomId((long) horario.getIdSala());
                    lessonUnitDto.setBlockSize((double) UNIT / HOUR);
                    lessonUnitDtos.add(lessonUnitDto);
                }

                lessonUnits -= horarioUnits;
            }

            if (horariosDaDisciplina.isEmpty() || lessonUnits > 0) {
                for (int i = 0; i < lessonUnits; i++) {
                    LessonUnitDto lessonUnitDto = new LessonUnitDto();
                    lessonUnitDto.setId(id++);
                    lessonUnitDto.setLessonId(lessonDto.getId());
                    lessonUnitDtos.add(lessonUnitDto);
                }
            }
        }

        return lessonUnitDtos;
    }
}
