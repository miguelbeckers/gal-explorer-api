package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class SubjectCourseDto {
    private Long id;
    private Long course;
    private Long subject;
    private Long period;
}
