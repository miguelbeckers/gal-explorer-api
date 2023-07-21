package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.Sala;
import pt.ipb.galoptimizer.repository.SalaRepository;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> findAll(){
        return salaRepository.findAll();
    }

    public Sala findById(Integer id){
        return salaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "sala não encontrada"));
    }
}
