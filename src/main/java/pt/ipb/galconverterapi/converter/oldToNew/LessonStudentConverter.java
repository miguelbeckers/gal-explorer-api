package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.LessonStudent;
import pt.ipb.galconverterapi.model._new.Student;
import pt.ipb.galconverterapi.model.old.AlunoDisciplina;
import pt.ipb.galconverterapi.repository._new.StudentRepository;
import pt.ipb.galconverterapi.repository.old.AlunoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonStudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<LessonStudent> convert() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<LessonStudent> lessonStudents = new ArrayList<>();

        List<Student> students = studentRepository.findAll();

        for(AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            LessonStudent lessonStudent = new LessonStudent();
            lessonStudent.setId((long) alunoDisciplina.getId());

            Student lessonStudentStudent = students.stream()
                    .filter(student -> student.getId() == alunoDisciplina.getIdAluno())
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("Student not found for id: " + alunoDisciplina.getIdAluno())
                    );

            lessonStudent.setStudent(lessonStudentStudent.getId());
            lessonStudents.add(lessonStudent);
        }

        return lessonStudents;
    }
}
