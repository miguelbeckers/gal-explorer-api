package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Dia;
import pt.ipb.galconverterapi.model.Tempo;
import pt.ipb.galconverterapi.repository.DiaRepository;
import pt.ipb.galconverterapi.repository.TempoRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeslotDtoConverter {
    @Autowired
    private TempoRepository tempoRepository;

    @Autowired
    private DiaRepository diaRepository;

    public List<TimeslotDto> convert() {
        List<Tempo> tempos = tempoRepository.findAll();
        List<Dia> dias = diaRepository.findAll();

        List<TimeslotDto> timeslots = new ArrayList<>();
        for (Tempo tempo : tempos) {
            for (Dia dia : dias) {
                TimeslotDto timeslot = new TimeslotDto();
                timeslot.setDayOfWeek(DayOfWeek.of(dia.getWeekday()));
                timeslot.setStartTime(tempo.getInicio().toLocalTime());
                timeslot.setEndTime(tempo.getFim().toLocalTime());

                timeslots.add(timeslot);
            }
        }

        return timeslots;
    }
}
