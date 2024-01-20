package pt.ipb.galconverterapi.dto;

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
}
