package pt.ipb.galoptimizer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.data.model.Recurso;
import pt.ipb.galoptimizer.data.repository.RecursoRepository;

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
