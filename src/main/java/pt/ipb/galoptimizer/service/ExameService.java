package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.Exame;
import pt.ipb.galoptimizer.repository.ExameRepository;

import java.util.List;

@Service
public class ExameService {
    @Autowired
    private ExameRepository exameRepository;

    public List<Exame> findAll(){
        return exameRepository.findAll();
    }

    public Exame findById(Integer id){
        return exameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exame não encontrado"));
    }
}
