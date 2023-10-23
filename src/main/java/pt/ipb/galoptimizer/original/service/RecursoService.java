package pt.ipb.galoptimizer.original.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.original.model.Recurso;
import pt.ipb.galoptimizer.original.repository.RecursoRepository;

import java.util.List;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> findAll(){
        return recursoRepository.findAll();
    }

    public Recurso findById(Integer id){
        return recursoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recurso não encontrado"));
    }
}
