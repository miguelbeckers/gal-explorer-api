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
    private final TempoRepository tempoRepository;
    private final DiaRepository diaRepository;
    private List<TimeslotDto> timeslotDtos = new ArrayList<>();
    private Boolean converted = false;

    @Autowired
    public TimeslotConverter(TempoRepository tempoRepository, DiaRepository diaRepository) {
        this.tempoRepository = tempoRepository;
        this.diaRepository = diaRepository;
    }

    public List<TimeslotDto> convert() {
        List<Tempo> tempos = tempoRepository.findAll();
        List<Dia> dias = diaRepository.findAll();

        long id = 1;
        List<TimeslotDto> timeslotDtos = new ArrayList<>();

        for (Dia dia : dias) {
            for (Tempo tempo : tempos) {
                TimeslotDto timeslotDto = new TimeslotDto();
                timeslotDto.setId(id++);
                timeslotDto.setDayOfWeek(DayOfWeek.of(dia.getWeekday()));
                timeslotDto.setStartTime(tempo.getInicio().toLocalTime());
                timeslotDto.setEndTime(tempo.getFim().toLocalTime());
                timeslotDtos.add(timeslotDto);
            }
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
