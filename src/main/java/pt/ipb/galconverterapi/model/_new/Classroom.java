package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Classroom {
    private Long id;
    private String name;
    private String abbreviation;
    private List<Timeslot> unavailability = new ArrayList<>();
    private List<ClassroomResource> classroomResources = new ArrayList<>();
}
