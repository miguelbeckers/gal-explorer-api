package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.CursoEpoca;
import pt.ipb.galoptimizer.repository.CursoEpocaRepository;

import java.util.List;

@Service
public class CursoEpocaService {
    @Autowired
    private CursoEpocaRepository cursoEpocaRepository;

    public List<CursoEpoca> findAll(){
        return cursoEpocaRepository.findAll();
    }

    public CursoEpoca findById(Integer id){
        return cursoEpocaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "curso-epoca não encontrado"));
    }
}
