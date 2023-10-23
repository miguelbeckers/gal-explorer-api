package pt.ipb.galoptimizer.original.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.original.model.RecursoSala;
import pt.ipb.galoptimizer.original.repository.RecursoSalaRepository;

import java.util.List;

@Service
public class RecursoSalaService {
    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    public List<RecursoSala> findAll(){
        return recursoSalaRepository.findAll();
    }

    public RecursoSala findById(Integer id){
        return recursoSalaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recurso-sala não encontrado"));
    }
}
