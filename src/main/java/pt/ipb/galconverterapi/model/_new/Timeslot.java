package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class Timeslot {
    private Long id;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
