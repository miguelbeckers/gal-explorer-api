package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {
    private Long id;
    private String code;
    private List<SubjectCourse> subjectCourses = new ArrayList<>();
}
