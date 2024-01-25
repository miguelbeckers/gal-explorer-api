package pt.ipb.galconverterapi.converter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.model.*;
import pt.ipb.galconverterapi.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class LessonConverter {
    private final DetalhesAulaRepository detalhesAulaRepository;
    private final DisciplinaCursoRepository disciplinaCursoRepository;
    private final AulaDocenteRepository aulaDocenteRepository;
    private final RecursoDisciplinaRepository recursoDisciplinaRepository;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final TipoAulaDisciplinaRepository tipoAulaDisciplinaRepository;
    private final TipoAulaRepository tipoAulaRepository;

    @Getter
    private List<LessonDto> lessonDtos = new ArrayList<>();

    @Getter
    private Boolean isConverted = false;

    @Autowired
    public LessonConverter(DetalhesAulaRepository detalhesAulaRepository,
                           DisciplinaCursoRepository disciplinaCursoRepository,
                           AulaDocenteRepository aulaDocenteRepository,
                           RecursoDisciplinaRepository recursoDisciplinaRepository,
                           AlunoDisciplinaRepository alunoDisciplinaRepository,
                           TipoAulaDisciplinaRepository tipoAulaDisciplinaRepository,
                           TipoAulaRepository tipoAulaRepository) {
        this.detalhesAulaRepository = detalhesAulaRepository;
        this.disciplinaCursoRepository = disciplinaCursoRepository;
        this.aulaDocenteRepository = aulaDocenteRepository;
        this.recursoDisciplinaRepository = recursoDisciplinaRepository;
        this.alunoDisciplinaRepository = alunoDisciplinaRepository;
        this.tipoAulaDisciplinaRepository = tipoAulaDisciplinaRepository;
        this.tipoAulaRepository = tipoAulaRepository;
    }

    public List<LessonDto> convert() {
        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<TipoAulaDisciplina> tipoAulaDisciplinas = tipoAulaDisciplinaRepository.findAll();
        List<TipoAula> tipoAulas = tipoAulaRepository.findAll();

        HashMap<Integer, TipoAula> tipoAulaHashMap = new HashMap<>();
        for (TipoAula tipoAula : tipoAulas) {
            tipoAulaHashMap.put(tipoAula.getId(), tipoAula);
        }

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
                            .filter(recursoDisciplina ->
                                    recursoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip()
                                            && recursoDisciplina.getIdTipoAula() == detalhesAula.getIdTipoAula()
                                            && recursoDisciplina.getIdAno() == detalhesAula.getIdAno()
                                            && recursoDisciplina.getIdCurso() == detalhesAula.getIdCurso()
                            )
                            .map(recursoDisciplina -> (long) recursoDisciplina.getId())
                            .toList());

//                    lessonDto.setStudents(alunoDisciplinas.stream()
//                            .filter(alunoDisciplina -> alunoDisciplina.getIdDiscip() == detalhesAula.getIdDiscip())
//                            .map(alunoDisciplina -> (long) alunoDisciplina.getIdAluno())
//                            .toList());

                    TipoAulaDisciplina tipoAulaDisciplina = tipoAulaDisciplinas.stream()
                            .filter(tad -> tad.getIdDiscip() == detalhesAula.getIdDiscip()
                                    && tad.getIdTipoAula() == detalhesAula.getIdTipoAula()
                            )
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "TipoAulaDisciplina not found for detalhesAula: " + detalhesAula.getId()
                                            + " with idDiscip: " + detalhesAula.getIdDiscip()
                                            + " idTipoAula: " + detalhesAula.getIdTipoAula()
                            ));

                    TipoAula tipoAula = tipoAulaHashMap.get(tipoAulaDisciplina.getIdTipoAula());

                    lessonDto.setHoursPerWeek(tipoAulaDisciplina.getHorasSemanais());
                    lessonDto.setBlocks(tipoAulaDisciplina.getNumBlocos());
                    lessonDto.setColor(tipoAula.getCor());
                    lessonDtos.add(lessonDto);
                }
            }
        }

        this.lessonDtos = lessonDtos;
        this.isConverted = true;

        return lessonDtos;
    }
}
