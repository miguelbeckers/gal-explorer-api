package pt.ipb.galoptimizer.schema1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.schema1.model.Aula;
import pt.ipb.galoptimizer.schema1.repository.AulaRepository;

import java.util.List;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    public List<Aula> findAll(){
        return aulaRepository.findAll();
    }

    public Aula findById(Integer id){
        return aulaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "aula não encontrada"));
    }
}
