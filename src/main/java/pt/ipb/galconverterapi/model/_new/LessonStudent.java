package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class LessonStudent {
    private Long id;
//    private Lesson lesson;
    private Student student;
}
