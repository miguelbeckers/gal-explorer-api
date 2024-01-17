package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Classroom {
    private Long id;
    private String name;
    private String abbreviation;
    private List<Long> unavailability = new ArrayList<>();
    private List<Long> classroomResources = new ArrayList<>();
}
