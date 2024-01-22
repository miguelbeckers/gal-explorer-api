package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class SubjectCourseDto {
    private Long id;
    private Long courseId;
    private Long subjectId;
    private Long periodId;
}
