package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.model.*;
import pt.ipb.galconverterapi.repository.*;

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

    public List<LessonDto> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();

        List<LessonDto> lessonDtos = new ArrayList<>();
        for (DetalhesAula detalhesAula : detalhesAulas) {
            for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
                if (detalhesAula.getIdDiscip() == disciplinaCurso.getIdDiscip()
                        && detalhesAula.getIdCurso() == disciplinaCurso.getIdCurso()
                        && detalhesAula.getIdAno() == disciplinaCurso.getIdAno()) {
                    LessonDto lessonDto = new LessonDto();
                    lessonDto.setId((long) detalhesAula.getId());
                    lessonDto.setName(detalhesAula.getTurma());
                    lessonDto.setSubjectCourse((long) disciplinaCurso.getId());
                    lessonDto.setSubjectType((long) detalhesAula.getIdTipoAula());

                    lessonDto.setProfessors(aulaDocentes.stream()
                            .filter(aulaDocente -> aulaDocente.getIdAula() == detalhesAula.getIdAula())
                            .map(aulaDocente -> (long) aulaDocente.getIdDocente())
                            .toList());

                    lessonDto.setLessonResources(recursoDisciplinas.stream()
                            .filter(recursoDisciplina -> recursoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
                            .map(recursoDisciplina -> (long) recursoDisciplina.getId())
                            .toList());

//                    lesson.setLessonStudents(alunoDisciplinas.stream()
//                            .filter(alunoDisciplina -> alunoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
//                            .map(alunoDisciplina -> (long) alunoDisciplina.getId())
//                            .toList());

                    lessonDtos.add(lessonDto);
                }
            }
        }

        return lessonDtos;
    }
}
