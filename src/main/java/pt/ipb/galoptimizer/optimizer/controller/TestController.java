package pt.ipb.galoptimizer.optimizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galoptimizer.original.model.*;
import pt.ipb.galoptimizer.original.repository.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello test-controller");
    }

    @GetMapping("/departamentos")
    public ResponseEntity<Object> departamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();

        if(departamentos.isEmpty()) {
            return ResponseEntity.ok("Não há departamentos");
        }

        List<Docente> docentes = docenteRepository.findByIdDepart(departamentos.get(0).getId());

        if(docentes.isEmpty()) {
            return ResponseEntity.ok("Não há docentes");
        }

        List<AulaDocente> aulaDocentes = aulaDocenteRepository.findByIdDocente(docentes.get(0).getId());

        if(aulaDocentes.isEmpty()) {
            return ResponseEntity.ok("Não há aula-docentes");
        }

        return ResponseEntity.ok(aulaDocentes.get(0));
    }

    @GetMapping("/cursos")
    public ResponseEntity<Object> cursos() {
        List<Curso> cursos = cursoRepository.findAll();

        if(cursos.isEmpty()) {
            return ResponseEntity.ok("Não há cursos");
        }

        Curso curso = cursos.get(1);

        List<AnoCurso> anoCursos = anoCursoRepository.findByIdCurso(curso.getId());

        if (anoCursos.isEmpty()) {
            return ResponseEntity.ok("Não há ano cursos");
        }

        Optional<Ano> ano = anoRepository.findById(anoCursos.get(0).getIdAno());

        if (ano.isEmpty()) {
            return ResponseEntity.ok("Não há anos");
        }

        List<DetalhesAula> detalhesAulas = detalhesAulaRepository.findByIdAnoAndIdCurso(ano.get().getId(), curso.getId());

        if (detalhesAulas.isEmpty()) {
            return ResponseEntity.ok("Não há detalhes aulas");
        }

        DetalhesAula detalhesAula = detalhesAulas.get(0);

        Disciplina disciplina = disciplinaRepository.findById(detalhesAula.getIdDiscip()).orElse(null);

        if (disciplina == null) {
            return ResponseEntity.ok("Não há disciplinas");
        }

        TipoAula tipoAula = tipoAulaRepository.findById(detalhesAula.getIdTipoAula()).orElse(null);

        if (tipoAula == null) {
            return ResponseEntity.ok("Não há tipo aulas");
        }

        String retorno = "Curso: " + curso.getNome() + "\n" +
                ", Ano: " + ano.get().getNome() + "\n" +
                ", Disciplina: " + disciplina.getNome() + "\n" +
                ", Tipo Aula: " + tipoAula.getNome() + "\n";

        return ResponseEntity.ok(retorno);
    }
}
