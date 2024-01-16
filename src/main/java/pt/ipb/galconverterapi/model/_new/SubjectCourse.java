package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class SubjectCourse {
    private Long id;
    private Course course;
    private Subject subject;
    private Period period;

    private int numAlunos;
}
