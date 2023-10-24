package pt.ipb.galoptimizer.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.data.dto.AnoDto;
import pt.ipb.galoptimizer.data.dto.CursoDto;
import pt.ipb.galoptimizer.data.dto.DepartamentoDto;
import pt.ipb.galoptimizer.data.dto.DisciplinaDto;
import pt.ipb.galoptimizer.data.model.*;
import pt.ipb.galoptimizer.data.repository.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private AulaDocenteRepository aulaDocenteRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    AnoCursoRepository anoCursoRepository;

    @Autowired
    DetalhesAulaRepository detalhesAulaRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AnoRepository anoRepository;

    @Autowired
    TipoAulaRepository tipoAulaRepository;

    @Autowired
    RecursoDisciplinaRepository recursoDisciplinaRepository;

    @Autowired
    RecursoRepository recursoRepository;

    @Autowired
    RecursoSalaRepository recursoSalaRepository;

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello test-controller");
    }

    @GetMapping("/departamento")
    public ResponseEntity<Object> departamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();

        List<DepartamentoDto> departamentoDtos = new ArrayList<>();
        for(Departamento departamento : departamentos) {
            List<Disciplina> disciplinas = disciplinaRepository.findByIdDepart(departamento.getId());

            List<DisciplinaDto> disciplinaDtos = new ArrayList<>();
            for (Disciplina disciplina : disciplinas) {
                DisciplinaDto disciplinaDto = new DisciplinaDto();
                disciplinaDto.setId(disciplina.getId());
                disciplinaDto.setNome(disciplina.getNome());
                disciplinaDtos.add(disciplinaDto);
            }

            disciplinaDtos.sort(Comparator.comparing(DisciplinaDto::getNome));

            DepartamentoDto departamentoDto = new DepartamentoDto();
            departamentoDto.setId(departamento.getId());
            departamentoDto.setNome(departamento.getNome());
            departamentoDto.setAbrev(departamento.getAbrev());
            departamentoDto.setDisciplinaDtos(disciplinaDtos);
            departamentoDtos.add(departamentoDto);
        }

        departamentoDtos.sort(Comparator.comparing(DepartamentoDto::getAbrev));
        return ResponseEntity.ok().body(departamentoDtos);
    }

    @GetMapping("/curso")
    public ResponseEntity<Object> cursos() {
        List<Curso> cursos = cursoRepository.findAll();

        List<CursoDto> cursoDtos = new ArrayList<>();
        for (Curso curso : cursos) {
            CursoDto cursoDto = new CursoDto();
            cursoDto.setId(curso.getId());
            cursoDto.setNome(curso.getNome());
            cursoDtos.add(cursoDto);
        }

        for (CursoDto cursoDto : cursoDtos) {
            List<AnoCurso> anoCursos = anoCursoRepository.findByIdCurso(cursoDto.getId());

            List<AnoDto> anoDtos = new ArrayList<>();
            for (AnoCurso anoCurso: anoCursos) {
                Ano ano = anoRepository.findById(anoCurso.getIdAno()).orElse(null);

                if (ano == null) {
                    continue;
                }

                List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findByIdAnoAndIdCurso(ano.getId(), cursoDto.getId());

                List<DisciplinaDto> disciplinaDtos = new ArrayList<>();
                for (DetalhesAula detalhesAula : detalhesAulas) {
                    Disciplina disciplina = disciplinaRepository.findById(detalhesAula.getIdDiscip()).orElse(null);
                    DisciplinaDto disciplinaDto = new DisciplinaDto();
                    disciplinaDto.setId(disciplina.getId());
                    disciplinaDto.setNome(disciplina.getNome());
                    disciplinaDtos.add(disciplinaDto);
                }

                AnoDto anoDto = new AnoDto();
                anoDto.setId(ano.getId());
                anoDto.setNome(ano.getNome());
                anoDto.setDisciplinaDtos(disciplinaDtos);
                anoDtos.add(anoDto);
            }

            anoDtos.sort(Comparator.comparing(AnoDto::getNome));
            cursoDto.setAnoDtos(anoDtos);
        }

        return ResponseEntity.ok(cursoDtos);
    }

    @GetMapping("/aula-docente")
    public ResponseEntity<Object> aulaDocente() {
        return null;

//        Curso curso = cursos.get(1);
//
//        List<AnoCurso> anoCursos = anoCursoRepository.findByIdCurso(curso.getId());
//
//        if (anoCursos.isEmpty()) {
//            return ResponseEntity.ok("Não há ano cursos");
//        }
//
//        Optional<Ano> ano = anoRepository.findById(anoCursos.get(0).getIdAno());
//
//        if (ano.isEmpty()) {
//            return ResponseEntity.ok("Não há anos");
//        }
//
//        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findByIdAnoAndIdCurso(ano.get().getId(), curso.getId());
//
//        if (detalhesAulas.isEmpty()) {
//            return ResponseEntity.ok("Não há detalhes aulas");
//        }
//
//        DetalhesAula detalhesAula = detalhesAulas.get(0);
//
//        Disciplina disciplina = disciplinaRepository.findById(detalhesAula.getIdDiscip()).orElse(null);
//
//        if (disciplina == null) {
//            return ResponseEntity.ok("Não há disciplinas");
//        }
//
//        TipoAula tipoAula = tipoAulaRepository.findById(detalhesAula.getIdTipoAula()).orElse(null);
//
//        if (tipoAula == null) {
//            return ResponseEntity.ok("Não há tipo aulas");
//        }
//
//        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findByIdDiscip(disciplina.getId());
//
//        if (recursoDisciplinas.isEmpty()) {
//            return ResponseEntity.ok("Não há recurso disciplinas");
//        }
//
//        RecursoDisciplina recursoDisciplina = recursoDisciplinas.get(0);
//
//        Recurso recurso = recursoRepository.findById(recursoDisciplina.getIdRec()).orElse(null);
//
//        if (recurso == null) {
//            return ResponseEntity.ok("Não há recursos");
//        }
//
//        List<RecursoSala> recursoSalas = recursoSalaRepository.findByIdRec(recurso.getId());
//
//        if (recursoSalas.isEmpty()) {
//            return ResponseEntity.ok("Não há recurso salas");
//        }
//
//        RecursoSala recursoSala = recursoSalas.get(0);
//
//        Sala sala = salaRepository.findById(recursoSala.getIdSala()).orElse(null);
//
//        List<Horario> horarios = horarioRepository.findByIdSala(sala.getId());
//
//        if (horarios.isEmpty()) {
//            return ResponseEntity.ok("Não há horarios");
//        }
//
//        Horario horario = horarios.get(0);
//
//        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findByIdAula(horario.getIdAula());
//
//        if (aulaDocentes.isEmpty()) {
//            return ResponseEntity.ok("Não há aula docentes");
//        }
//
//        AulaDocente aulaDocente = aulaDocentes.get(0);
//
//        Docente docente = docenteRepository.findById(aulaDocente.getIdDocente()).orElse(null);
//
//        if (docente == null) {
//            return ResponseEntity.ok("Não há docentes");
//        }
//
//        String retorno = "Curso: " + curso.getNome() + "\n" +
//                ", Ano: " + ano.get().getNome() + "\n" +
//                ", Disciplina: " + disciplina.getNome() + "\n" +
//                ", Tipo Aula: " + tipoAula.getNome() + "\n" +
//                ", Recurso: " + recurso.getNome() + "\n" +
//                ", Sala: " + sala.getNome() + "\n" +
//                ", Horario: " + horario.getInicio() + " - " + horario.getFim() + "\n" +
//                ", Docente: " + docente.getNome() + " (" + docente.getAbrev() + ") " + "\n";
//
//        return ResponseEntity.ok(retorno);
    }
}
