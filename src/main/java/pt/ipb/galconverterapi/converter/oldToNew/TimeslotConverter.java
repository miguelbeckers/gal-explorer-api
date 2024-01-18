package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Dia;
import pt.ipb.galconverterapi.model.old.Tempo;
import pt.ipb.galconverterapi.repository.old.DiaRepository;
import pt.ipb.galconverterapi.repository.old.TempoRepository;

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

    private List<Timeslot> timeslots = new ArrayList<>();

    private Boolean converted = false;

    public List<Timeslot> convert() {
        List<Tempo> tempos = tempoRepository.findAll();
        List<Dia> dias = diaRepository.findAll();

        List<Timeslot> timeslots = new ArrayList<>();
        long id = 1;
        for (Tempo tempo : tempos) {
            for (Dia dia : dias) {
                Timeslot timeslot = new Timeslot();
                timeslot.setId(id++);
                timeslot.setDayOfWeek(DayOfWeek.of(dia.getWeekday()));
                timeslot.setStartTime(tempo.getInicio().toLocalTime());
                timeslot.setEndTime(tempo.getFim().toLocalTime());
                timeslots.add(timeslot);
            }
        }

        this.timeslots = timeslots;
        converted = true;

        return timeslots;
    }

    public List<Timeslot> convert(List<Object[]> indisponibilidades) {
        if (!converted) convert();
        List<Timeslot> unavailability = new ArrayList<>();

        for (Object[] indisponibilidade : indisponibilidades) {
            DayOfWeek dayOfWeek = DayOfWeek.of((int) indisponibilidade[2]);
            Time startTime = (Time) indisponibilidade[3];
            Time endTime = (Time) indisponibilidade[4];

            for (Timeslot timeslot : timeslots) {
                if (timeslot.getDayOfWeek().equals(dayOfWeek)
                        && timeslot.getStartTime().equals(startTime.toLocalTime())
                        && timeslot.getEndTime().equals(endTime.toLocalTime())) {
                    unavailability.add(timeslot);
                    break;
                }
            }
        }

        return unavailability;
    }
}
