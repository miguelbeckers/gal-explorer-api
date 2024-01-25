package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
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
public class LessonUnitConverter {
    private final LessonConverter lessonConverter;
    private final TimeslotConverter timeslotConverter;
    private final HorarioRepository horarioRepository;
    private static final int LESSON_UNIT_DURATION = 30;

    @Autowired
    public LessonUnitConverter(LessonConverter lessonConverter,
                               HorarioRepository horarioRepository,
                               TimeslotConverter timeslotConverter) {
        this.lessonConverter = lessonConverter;
        this.horarioRepository = horarioRepository;
        this.timeslotConverter = timeslotConverter;
    }

    public List<LessonUnitDto> convert() {
        List<Horario> horarios = horarioRepository.findAll();

        List<LessonUnitDto> lessonsWithTimeslotsAndClassroom = createUnitsWithTimeslotsAndClassroom();
        List<LessonUnitDto> lessonsWithoutTimeslotAndClassroom = createUntisWithoutTimeslotsAndClassroom();

//        if (lessonsWithTimeslotsAndClassroom.size() != lessonsWithoutTimeslotAndClassroom.size())
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "LessonUnitDto lists have different sizes."
//                    + " lessonsWithTimeslotsAndClassroom.size() = " + lessonsWithTimeslotsAndClassroom.size()
//                    + " lessonsWithoutTimeslotAndClassroom.size() = " + lessonsWithoutTimeslotAndClassroom.size()
//                    + " horarios.size() = " + horarios.size());

        return lessonsWithoutTimeslotAndClassroom;

//        return createUnitsWithTimeslotsAndClassroom();
//        return createUntisWithoutTimeslotsAndClassroom();
    }

    private List<LessonUnitDto> createUntisWithoutTimeslotsAndClassroom() {
        List<LessonDto> lessonDtos = lessonConverter.getIsConverted() ?
                lessonConverter.getLessonDtos() : lessonConverter.convert();

        List<LessonUnitDto> lessonUnitDtos = new ArrayList<>();
        long id = 1L;

        int count = 0;

        for (LessonDto lessonDto : lessonDtos) {
            int units = (int) Math.round(lessonDto.getHoursPerWeek() * 60 / LESSON_UNIT_DURATION);
            System.out.println(" lessonDto.getId() = " + lessonDto.getId()
                    + ", lessonDto.getHoursPerWeek() = " + lessonDto.getHoursPerWeek()
                    + ", units = " + units);

            for (int i = 0; i < units; i++) {
                LessonUnitDto lessonUnitDto = new LessonUnitDto();
                lessonUnitDto.setId(id++);
                lessonUnitDto.setLessonId(lessonDto.getId());
                lessonUnitDtos.add(lessonUnitDto);
            }

            count += units;
        }

        System.out.println("final count of the classes WITHOUT timeslot and classroom = " + count);

        return lessonUnitDtos;
    }

    private List<LessonUnitDto> createUnitsWithTimeslotsAndClassroom() {
        List<Horario> horarios = horarioRepository.findAll();

        List<LessonDto> lessonDtos = lessonConverter.getIsConverted() ?
                lessonConverter.getLessonDtos() : lessonConverter.convert();

        List<TimeslotDto> timeslotDtos = timeslotConverter.getIsConverted() ?
                timeslotConverter.getTimeslotDtos() : timeslotConverter.convert();

        List<LessonUnitDto> lessonUnitDtos = new ArrayList<>();
        long id = 1L;

        for (LessonDto lessonDto : lessonDtos) {
            List<Horario> horariosDaDisciplina = horarios.stream()
                    .filter(h -> h.getIdAula() == lessonDto.getId())
                    .toList();

            int count = 0;

            for (Horario horario : horariosDaDisciplina) {
                Duration duration = Duration.between(horario.getInicio().toLocalTime(), horario.getFim().toLocalTime());
                int units = Math.round((float) duration.toMinutes() / LESSON_UNIT_DURATION);

                System.out.println(" lessonDto.getId() = " + lessonDto.getId()
                        + ", lessonDto.getHoursPerWeek() = " + lessonDto.getHoursPerWeek()
                        + ", units = " + units);

                for (int i = 0; i < units; i++) {
                    LocalTime startTime = horario.getInicio().toLocalTime().plus(Duration.ofMinutes((long) i * LESSON_UNIT_DURATION));
                    LocalTime endTime = horario.getInicio().toLocalTime().plus(Duration.ofMinutes((long) (i + 1) * LESSON_UNIT_DURATION));

                    TimeslotDto timeslotDto = timeslotDtos.stream()
                            .filter(t -> t.getDayOfWeek().equals(DayOfWeek.of(horario.getIdDia()))
                                    && t.getStartTime().equals(startTime)
                                    && t.getEndTime().equals(endTime))
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Timeslot not found: "
                                    + horario.getIdDia() + " "
                                    + horario.getInicio().toLocalTime() + " "
                                    + horario.getInicio().toLocalTime()
                                    .plus(Duration.ofMinutes(LESSON_UNIT_DURATION))));

                    LessonUnitDto lessonUnitDto = new LessonUnitDto();
                    lessonUnitDto.setId(id++);
                    lessonUnitDto.setLessonId(lessonDto.getId());
                    lessonUnitDto.setTimeslotId(timeslotDto.getId());
                    lessonUnitDto.setClassroomId((long) horario.getIdSala());
                    lessonUnitDtos.add(lessonUnitDto);
                }

                count += units;
            }
        }

        System.out.println("final count of the classes WITH timeslot and classroom = " + lessonUnitDtos.size());

        return lessonUnitDtos;
    }
}
