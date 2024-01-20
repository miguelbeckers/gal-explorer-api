package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galconverterapi.dto.LessonUnitDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Dia;
import pt.ipb.galconverterapi.model.Horario;
import pt.ipb.galconverterapi.model.Tempo;
import pt.ipb.galconverterapi.repository.DiaRepository;
import pt.ipb.galconverterapi.repository.HorarioRepository;
import pt.ipb.galconverterapi.repository.TempoRepository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class HorarioConverter {
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private TempoRepository tempoRepository;

    public List<Horario> convert(List<LessonUnitDto> lessonUntiDtos) {
        List<TimeslotDto> timeslotDtos = timeslotConverter.convert();
        List<Dia> dias = diaRepository.findAll();
        List<Tempo> tempos = tempoRepository.findAll();

        HashMap<Long, TimeslotDto> timeslotMap = new HashMap<>();
        for (TimeslotDto timeslotDto : timeslotDtos) {
            timeslotMap.put(timeslotDto.getId(), timeslotDto);
        }

        List<Horario> horarios = new ArrayList<>();

        // TODO: identify sequence of timeslots to create blocks of lessons
        for (LessonUnitDto lessonDto : lessonUntiDtos) {
            TimeslotDto timeslotDto = timeslotMap.get(lessonDto.getId());

            Dia dia = dias.stream()
                    .filter(d -> d.getWeekday() == timeslotDto.getDayOfWeek().getValue())
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Dia not found: " + timeslotDto.getDayOfWeek().getValue()));

            Time inicio = Time.valueOf(timeslotDto.getStartTime());
            Time fim = Time.valueOf(timeslotDto.getEndTime());

            Tempo tempo = tempos.stream()
                    .filter(t -> t.getInicio().equals(inicio) && t.getFim().equals(fim))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Tempo not found: " + timeslotDto.getStartTime() + " - " + timeslotDto.getEndTime()));

            Horario horario = new Horario();
            horario.setId(timeslotDto.getId().intValue());
            horario.setIdDia(dia.getId());
            horario.setInicio(tempo.getInicio());
            horario.setFim(tempo.getFim());
            horario.setIdSala(lessonDto.getClassroom().intValue());
            horarios.add(horario);
        }

        horarioRepository.deleteAll();
        return horarioRepository.saveAll(horarios);
    }
}
