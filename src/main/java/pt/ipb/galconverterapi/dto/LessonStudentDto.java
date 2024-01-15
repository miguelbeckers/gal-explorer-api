package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class LessonStudentDto {
    private Long id;
//    private Lesson lesson;
    private StudentDto studentDto;
}
