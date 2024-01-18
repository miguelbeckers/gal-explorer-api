package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.LessonStudent;
import pt.ipb.galconverterapi.model.old.AlunoDisciplina;
import pt.ipb.galconverterapi.repository.old.AlunoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonStudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<LessonStudent> convert() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<LessonStudent> lessonStudents = new ArrayList<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            LessonStudent lessonStudent = new LessonStudent();
            lessonStudent.setId((long) alunoDisciplina.getId());
            lessonStudent.setStudent((long) alunoDisciplina.getIdAluno());
            lessonStudents.add(lessonStudent);
        }

        return lessonStudents;
    }
}
