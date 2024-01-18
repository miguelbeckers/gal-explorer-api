package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Student;
import pt.ipb.galconverterapi.model.old.AlunoDisciplina;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.old.AlunoDisciplinaRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class StudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    public List<Student> convert() {
        return getStudentsWithoutSubjectCourses();
//        return getStudentsWithSubjectCourses();
    }

    public List<Student> getStudentsWithoutSubjectCourses(){
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();

        HashMap<Long, Student> studentMap = new HashMap<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Long id = (long) alunoDisciplina.getIdAluno();

            if (!studentMap.containsKey(id)) {
                Student student = new Student();
                student.setId(id);
                student.setSubjectCourses(List.of());
                studentMap.put(id, student);
            }
        }

        return new ArrayList<>(studentMap.values());
    }

    private List<Student> getStudentsWithSubjectCourses(){
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();

        HashMap<Long, Student> studentMap = new HashMap<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Long id = (long) alunoDisciplina.getIdAluno();

            DisciplinaCurso disciplinaCurso = disciplinaCursos.stream()
                    .filter(dc -> dc.getIdCurso() == alunoDisciplina.getIdCurso()
                            && dc.getIdDiscip() == alunoDisciplina.getIdDiscip()
                            && dc.getIdAno() == alunoDisciplina.getAno())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "DisciplinaCurso not found for alunoDisciplina: " + alunoDisciplina.getId()
                                    + " with idCurso: " + alunoDisciplina.getIdCurso()
                                    + " idDiscip: " + alunoDisciplina.getIdDiscip()
                                    + " idAno: " + alunoDisciplina.getAno()
                    ));

            if (!studentMap.containsKey(id)) {
                Student student = new Student();
                student.setId(id);
                student.setSubjectCourses(List.of((long) disciplinaCurso.getId()));
                studentMap.put(id, student);
            } else {
                Student student = studentMap.get(id);
                List<Long> subjectCourses = new ArrayList<>(student.getSubjectCourses());
                subjectCourses.add((long) disciplinaCurso.getId());
                student.setSubjectCourses(subjectCourses);
            }
        }

        return new ArrayList<>(studentMap.values());
    }
}
