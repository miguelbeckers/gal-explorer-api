package pt.ipb.galoptimizer.original.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.original.model.SalaExame;
import pt.ipb.galoptimizer.original.repository.SalaExameRepository;

import java.util.List;

@Service
public class SalaExameService {
    @Autowired
    private SalaExameRepository salaExameRepository;

    public List<SalaExame> findAll(){
        return salaExameRepository.findAll();
    }

    public SalaExame findById(Integer id){
        return salaExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sala-exame não encontrada"));
    }
}
