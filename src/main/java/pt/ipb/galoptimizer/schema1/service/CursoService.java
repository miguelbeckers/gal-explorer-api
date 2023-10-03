package pt.ipb.galoptimizer.schema1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.schema1.model.Curso;
import pt.ipb.galoptimizer.schema1.repository.CursoRepository;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll(){
        return cursoRepository.findAll();
    }

    public Curso findById(Integer id){
        return cursoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "curso não encontrado"));
    }
}
