package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Lesson;
import pt.ipb.galconverterapi.model.old.AulaDocente;
import pt.ipb.galconverterapi.model.old.DetalhesAula;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.old.AulaDocenteRepository;
import pt.ipb.galconverterapi.repository.old.DetalhesAulaRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

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

    public List<Lesson> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();

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

                    List<AulaDocente> aulaDocentesDetalheAula = aulaDocentes.stream()
                            .filter(aulaDocente -> aulaDocente.getIdAula() == detalhesAula.getIdAula()).toList();

                    lesson.setProfessors(aulaDocentesDetalheAula.stream()
                            .map(aulaDocente -> (long) aulaDocente.getIdDocente()).toList());

                    //TODO: insert lesson resources
                    //TODO: insert lesson students
                    lessons.add(lesson);
                }
            }
        }

        return lessons;
    }
}
