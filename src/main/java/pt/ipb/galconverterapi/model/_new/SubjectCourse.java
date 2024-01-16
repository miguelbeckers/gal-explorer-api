package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class SubjectCourse {
    private Long id;
    private Course course;
    private Subject subject;

    private int idCurso;
    private int idAno;
    private int numAlunos;
}
