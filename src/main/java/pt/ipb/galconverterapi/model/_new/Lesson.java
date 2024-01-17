package pt.ipb.galconverterapi.model._new;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lesson {
    private Long id;
    private String name;
    private List<Long> professors;
    private Long subjectCourse;
    private Long subjectType;
    private List<Long> lessonResources = new ArrayList<>();
    private List<Long> lessonStudents = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long timeslot;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long classroom;
}
