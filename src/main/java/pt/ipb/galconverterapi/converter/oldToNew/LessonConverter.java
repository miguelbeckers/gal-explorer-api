package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Lesson;
import pt.ipb.galconverterapi.model.old.*;
import pt.ipb.galconverterapi.repository.old.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonConverter {
    @Autowired
    private DetalhesAulaRepository detalhesAulaRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private AulaDocenteRepository aulaDocenteRepository;

    @Autowired
    private RecursoDisciplinaRepository recursoDisciplinaRepository;

    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<Lesson> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();

        List<Lesson> lessons = new ArrayList<>();
        for (DetalhesAula detalhesAula : detalhesAulas) {
            for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
                if (detalhesAula.getIdDiscip() == disciplinaCurso.getIdDiscip()
                        && detalhesAula.getIdCurso() == disciplinaCurso.getIdCurso()
                        && detalhesAula.getIdAno() == disciplinaCurso.getIdAno()) {
                    Lesson lesson = new Lesson();
                    lesson.setId((long) detalhesAula.getId());
                    lesson.setName(detalhesAula.getTurma());
                    lesson.setSubjectCourse((long) disciplinaCurso.getId());
                    lesson.setSubjectType((long) detalhesAula.getIdTipoAula());

                    lesson.setProfessors(aulaDocentes.stream()
                            .filter(aulaDocente -> aulaDocente.getIdAula() == detalhesAula.getIdAula())
                            .map(aulaDocente -> (long) aulaDocente.getIdDocente())
                            .toList());

                    lesson.setLessonResources(recursoDisciplinas.stream()
                            .filter(recursoDisciplina -> recursoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
                            .map(recursoDisciplina -> (long) recursoDisciplina.getId())
                            .toList());

//                    lesson.setLessonStudents(alunoDisciplinas.stream()
//                            .filter(alunoDisciplina -> alunoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
//                            .map(alunoDisciplina -> (long) alunoDisciplina.getId())
//                            .toList());

                    lessons.add(lesson);
                }
            }
        }

        return lessons;
    }
}
