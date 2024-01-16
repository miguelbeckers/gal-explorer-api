package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lesson {
    private Long id;
    private String name;
    private String color;
    private Professor professor;
    private SubjectCourse subjectCourse;
    private SubjectType subjectType;
    private List<LessonResource> lessonResources = new ArrayList<>();
    private List<LessonStudent> lessonStudents = new ArrayList<>();

    private Timeslot timeslot;
    private Classroom classroom;
}
