package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class LessonUnitDto {
    private Long id;
    private Long lessonId;
    private Long timeslotId;
    private Long classroomId;
}
