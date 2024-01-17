package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.List;

@Data
public class Timetable {
    private List<Long> timeslots;
    private List<Long> classrooms;
    private List<Long> lessons;
}
