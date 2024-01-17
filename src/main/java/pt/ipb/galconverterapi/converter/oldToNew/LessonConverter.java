package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Lesson;
import pt.ipb.galconverterapi.model._new.Professor;
import pt.ipb.galconverterapi.model._new.SubjectCourse;
import pt.ipb.galconverterapi.model._new.SubjectType;
import pt.ipb.galconverterapi.model.old.AulaDocente;
import pt.ipb.galconverterapi.model.old.DetalhesAula;
import pt.ipb.galconverterapi.model.old.DisciplinaCurso;
import pt.ipb.galconverterapi.repository._new.ProfessorRepository;
import pt.ipb.galconverterapi.repository._new.SubjectCourseRepository;
import pt.ipb.galconverterapi.repository._new.SubjectTypeRepository;
import pt.ipb.galconverterapi.repository.old.AulaDocenteRepository;
import pt.ipb.galconverterapi.repository.old.DetalhesAulaRepository;
import pt.ipb.galconverterapi.repository.old.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class LessonConverter {
    @Autowired
    private DetalhesAulaRepository detalhesAulaRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private SubjectTypeRepository subjectTypeRepository;

    @Autowired
    private AulaDocenteRepository aulaDocenteRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Lesson> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();

        List<SubjectType> subjectTypes = subjectTypeRepository.findAll();
        List<Professor> professors = professorRepository.findAll();

        HashMap<Long, SubjectType> subjectTypeHashMap = new HashMap<>();
        for (SubjectType subjectType : subjectTypes) {
            subjectTypeHashMap.put(subjectType.getId(), subjectType);
        }

        HashMap<Long, Professor> professorHashMap = new HashMap<>();
        for (Professor professor : professors) {
            professorHashMap.put(professor.getId(), professor);
        }

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

                    lesson.setSubjectType(subjectTypeHashMap.get((long) detalhesAula.getIdTipoAula()).getId());

                    List<AulaDocente> aulaDocentesDetalheAula = aulaDocentes.stream()
                            .filter(aulaDocente -> aulaDocente.getIdAula() == detalhesAula.getIdAula())
                            .toList();

                    List<Professor> lessonProfessors = new ArrayList<>();
                    for (AulaDocente aulaDocente : aulaDocentesDetalheAula) {
                        lessonProfessors.add(professorHashMap.get((long) aulaDocente.getIdDocente()));
                    }

                    lesson.setProfessors(lessonProfessors.stream()
                            .map(Professor::getId)
                            .toList());

                    //TODO: insert lesson resources
                    //TODO: insert lesson students
                    lessons.add(lesson);
                }
            }
        }

        return lessons;
    }
}
