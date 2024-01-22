package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
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

    @Autowired
    private TipoAulaDisciplinaRepository tipoAulaDisciplinaRepository;

    public List<LessonDto> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<TipoAulaDisciplina> tipoAulaDisciplinas = tipoAulaDisciplinaRepository.findAll();

        List<LessonDto> lessonDtos = new ArrayList<>();
        for (DetalhesAula detalhesAula : detalhesAulas) {
            for (DisciplinaCurso disciplinaCurso : disciplinaCursos) {
                if (detalhesAula.getIdDiscip() == disciplinaCurso.getIdDiscip()
                        && detalhesAula.getIdCurso() == disciplinaCurso.getIdCurso()
                        && detalhesAula.getIdAno() == disciplinaCurso.getIdAno()) {
                    LessonDto lessonDto = new LessonDto();
                    lessonDto.setId((long) detalhesAula.getIdAula());
                    lessonDto.setName(detalhesAula.getTurma());
                    lessonDto.setSubjectCourseId((long) disciplinaCurso.getId());
                    lessonDto.setSubjectTypeId((long) detalhesAula.getIdTipoAula());

                    lessonDto.setProfessorIds(aulaDocentes.stream()
                            .filter(aulaDocente -> aulaDocente.getIdAula() == detalhesAula.getIdAula())
                            .map(aulaDocente -> (long) aulaDocente.getIdDocente())
                            .toList());

                    lessonDto.setLessonResourceIds(recursoDisciplinas.stream()
                            .filter(recursoDisciplina -> recursoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
                            .map(recursoDisciplina -> (long) recursoDisciplina.getId())
                            .toList());

//                    lessonDto.setStudents(alunoDisciplinas.stream()
//                            .filter(alunoDisciplina -> alunoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
//                            .map(alunoDisciplina -> (long) alunoDisciplina.getIdAluno())
//                            .toList());

                    TipoAulaDisciplina tipoAulaDisciplina = tipoAulaDisciplinas.stream()
                            .filter(tad -> tad.getIdDiscip() == detalhesAula.getIdDiscip()
                                    && tad.getIdTipoAula() == detalhesAula.getIdTipoAula())
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "TipoAulaDisciplina not found for detalhesAula: " + detalhesAula.getId()
                                            + " with idDiscip: " + detalhesAula.getIdDiscip()
                                            + " idTipoAula: " + detalhesAula.getIdTipoAula()
                            ));

                    lessonDto.setHoursPerWeek(tipoAulaDisciplina.getHorasSemanais());
                    lessonDto.setBlocks(tipoAulaDisciplina.getNumBlocos());
                    lessonDtos.add(lessonDto);
                }
            }
        }

        return lessonDtos;
    }
}
