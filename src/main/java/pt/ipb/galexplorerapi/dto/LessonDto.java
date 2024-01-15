package pt.ipb.galexplorerapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LessonDto {
    private Long id;
    private String name;
    private String color;
    private ProfessorDto professorDto;
    private SubjectCourseDto subjectCourseDto;
    private SubjectTypeDto subjectTypeDto;
    private List<LessonResourceDto> lessonResourceDtos = new ArrayList<>();
    private List<LessonStudentDto> lessonStudentDtos = new ArrayList<>();

    private TimeslotDto timeslotDto;
    private ClassroomDto classroomDto;
}
