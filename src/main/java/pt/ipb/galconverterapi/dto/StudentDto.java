package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String code;
    private List<SubjectCourseDto> subjectCoursDtos = new ArrayList<>();
}
