package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Student;
import pt.ipb.galconverterapi.model.old.AlunoDisciplina;
import pt.ipb.galconverterapi.repository.old.AlunoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<Student> convert() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();

        List<Student> students = new ArrayList<>();

        for(AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Student student = new Student();
            student.setId((long) alunoDisciplina.getId());

            if(!students.contains(student)) {
                students.add(student);
            }
        }

        //TODO: add subject courses to students

        return students;
    }
}
