package pt.ipb.galexplorerapi.dto;

import lombok.Data;

@Data
public class SubjectCourseDto {
    private Long id;
    private CourseDto courseDto;
    private SubjectDto subjectDto;
}
