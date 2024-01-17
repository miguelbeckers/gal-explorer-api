package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Lesson;
import pt.ipb.galconverterapi.model.old.AnoCurso;
import pt.ipb.galconverterapi.model.old.AulaDocente;
import pt.ipb.galconverterapi.model.old.DetalhesAula;
import pt.ipb.galconverterapi.model.old.Docente;
import pt.ipb.galconverterapi.repository.old.*;
import pt.ipb.galconverterapi.model._new.DetalhesAulaDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonConverter {
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private DetalhesAulaRepository detalhesAulaRepository;

    @Autowired
    private AnoCursoRepository anoCursoRepository;

    @Autowired
    private AulaDocenteRepository aulaDocenteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Lesson> convert() {
        List<Object[]> servicoDocentes = horarioRepository.findServicoDocentes();
        System.out.println("servicoDocentes size: " + servicoDocentes.size());

        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        System.out.println("detalhesAulas size: " + detalhesAulas.size());

        List<DetalhesAulaDto> detalhesAulaDtos = criarDetalhesAulaDtos();
        System.out.println("detalhesAulaDtos size: " + detalhesAulaDtos.size());

        return new ArrayList<>();
    }

    public List<DetalhesAulaDto> criarDetalhesAulaDtos() {

        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findAll();
        List<AnoCurso> anoCursos = anoCursoRepository.findAll();
        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findAll();
        List<Docente> docentes = docenteRepository.findAll();

        List<DetalhesAulaDto> detalhesAulaDTOs = new ArrayList<>();

        for (DetalhesAula detalhesAula : detalhesAulas) {
            for (AnoCurso anoCurso : anoCursos) {
                for (AulaDocente aulaDocente : aulaDocentes) {
                    for (Docente docente : docentes) {
                        if (detalhesAula.getIdAno() == anoCurso.getIdAno()
                                && detalhesAula.getIdCurso() == anoCurso.getIdCurso()
                                && detalhesAula.getIdAula() == aulaDocente.getIdAula()
                                && aulaDocente.getIdDocente() == docente.getId()) {

                            DetalhesAulaDto detalhesAulaDTO = new DetalhesAulaDto();

//                            anoCurso.getId(),
//                                    detalhesAula.getIdDiscip(),
//                                    detalhesAula.getIdTipoAula(),
//                                    docente.getId(),
//                                    detalhesAula.getTurma(),
//                                    detalhesAula.getIdAula(),
//                                    detalhesAula.getId()

                            detalhesAulaDTOs.add(detalhesAulaDTO);
                        }
                    }
                }
            }
        }

        return detalhesAulaDTOs;
    }
}
