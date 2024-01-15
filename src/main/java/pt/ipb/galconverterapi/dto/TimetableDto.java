package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TimetableDto {
    private List<TimeslotDto> timeslotDtos;
    private List<ClassroomDto> classroomDtos;
    private List<LessonDto> lessonDtos;

    public TimetableDto(List<TimeslotDto> timeslotDtos, List<ClassroomDto> classroomDtos, List<LessonDto> lessonDtos) {
        this.timeslotDtos = timeslotDtos;
        this.classroomDtos = classroomDtos;
        this.lessonDtos = lessonDtos;
    }
}
