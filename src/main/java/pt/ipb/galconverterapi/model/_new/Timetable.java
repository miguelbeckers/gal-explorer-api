package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.List;

@Data
public class Timetable {
    private List<Timeslot> timeslots;
    private List<Classroom> classrooms;
    private List<Lesson> lessons;

    public Timetable(List<Timeslot> timeslots, List<Classroom> classrooms, List<Lesson> lessons) {
        this.timeslots = timeslots;
        this.classrooms = classrooms;
        this.lessons = lessons;
    }
}
