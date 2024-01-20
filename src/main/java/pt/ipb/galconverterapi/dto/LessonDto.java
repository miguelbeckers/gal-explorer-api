package pt.ipb.galconverterapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonDto {
    private Long id;
    private String name;
    private Double hoursPerWeek;
    private List<Long> professors;
    private Long subjectCourse;
    private Long subjectType;
    private List<Long> lessonResources = new ArrayList<>();
    private List<Long> students = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long timeslot;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long classroom;
}
