package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Student;
import pt.ipb.galconverterapi.model._new.SubjectCourse;
import pt.ipb.galconverterapi.model.old.AlunoDisciplina;
import pt.ipb.galconverterapi.repository._new.SubjectCourseRepository;
import pt.ipb.galconverterapi.repository.old.AlunoDisciplinaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    private SubjectCourseRepository subjectCourseRepository;

    public List<Student> convert() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<SubjectCourse> subjectCourses = subjectCourseRepository.findAll();

        //TODO: check the usage fo HashMap
        Map<Long, Student> studentMap = new HashMap<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Long studentId = (long) alunoDisciplina.getIdAluno();

            if (studentMap.containsKey(studentId)) {
                continue;
            }

            Student student = new Student();
            student.setId(studentId);

            //TODO: check if a student can have more than one subject course
            List<SubjectCourse> studentSubjectCourses = subjectCourses.stream()
                    .filter(subjectCourse -> subjectCourse.getCourse().getId().equals((long) alunoDisciplina.getIdCurso())
                            && subjectCourse.getSubject().getId().equals((long) alunoDisciplina.getIdDiscip()))
                    .toList();

            student.setSubjectCourses(studentSubjectCourses);
            studentMap.put(studentId, student);
        }

        return new ArrayList<>(studentMap.values());
    }
}
