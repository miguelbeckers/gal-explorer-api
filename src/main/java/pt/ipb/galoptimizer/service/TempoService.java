package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.Tempo;
import pt.ipb.galoptimizer.repository.TempoRepository;

import java.util.List;

@Service
public class TempoService {
    @Autowired
    private TempoRepository tempoRepository;

    public List<Tempo> findAll(){
        return tempoRepository.findAll();
    }

    public Tempo findById(Integer id){
        return tempoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tempo não encontrado"));
    }
}
