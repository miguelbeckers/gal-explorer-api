package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Dia;
import pt.ipb.galconverterapi.model.Tempo;
import pt.ipb.galconverterapi.repository.DiaRepository;
import pt.ipb.galconverterapi.repository.TempoRepository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeslotConverter {
    @Autowired
    private TempoRepository tempoRepository;

    @Autowired
    private DiaRepository diaRepository;

    private List<TimeslotDto> timeslotDtos = new ArrayList<>();

    private Boolean converted = false;

    public List<TimeslotDto> convert() {
        List<Tempo> tempos = tempoRepository.findAll();
        List<Dia> dias = diaRepository.findAll();

        List<TimeslotDto> timeslotDtos = new ArrayList<>();
        for (Tempo tempo : tempos) {
            for (Dia dia : dias) {
                TimeslotDto timeslotDto = new TimeslotDto();
                timeslotDto.setDayOfWeek(DayOfWeek.of(dia.getWeekday()));
                timeslotDto.setStartTime(tempo.getInicio().toLocalTime());
                timeslotDto.setEndTime(tempo.getFim().toLocalTime());
                timeslotDtos.add(timeslotDto);
            }
        }

        timeslotDtos.sort((t1, t2) -> {
            if (t1.getDayOfWeek().getValue() < t2.getDayOfWeek().getValue()) return -1;
            if (t1.getDayOfWeek().getValue() > t2.getDayOfWeek().getValue()) return 1;
            if (t1.getStartTime().isBefore(t2.getStartTime())) return -1;
            if (t1.getStartTime().isAfter(t2.getStartTime())) return 1;
            return 0;
        });

        long id = 1;
        for (TimeslotDto timeslotDto : timeslotDtos) {
            timeslotDto.setId(id++);
        }

        this.timeslotDtos = timeslotDtos;
        converted = true;

        return timeslotDtos;
    }

    public List<TimeslotDto> convert(List<Object[]> indisponibilidades) {
        if (!converted) convert();
        List<TimeslotDto> unavailability = new ArrayList<>();

        for (Object[] indisponibilidade : indisponibilidades) {
            DayOfWeek dayOfWeek = DayOfWeek.of((int) indisponibilidade[2]);
            Time startTime = (Time) indisponibilidade[3];
            Time endTime = (Time) indisponibilidade[4];

            for (TimeslotDto timeslotDto : timeslotDtos) {
                if (timeslotDto.getDayOfWeek().equals(dayOfWeek)
                        && timeslotDto.getStartTime().equals(startTime.toLocalTime())
                        && timeslotDto.getEndTime().equals(endTime.toLocalTime())) {
                    unavailability.add(timeslotDto);
                    break;
                }
            }
        }

        return unavailability;
    }
}
