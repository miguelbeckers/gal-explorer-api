package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.IndispEscolaExame;
import pt.ipb.galoptimizer.repository.IndispEscolaExameRepository;

import java.util.List;

@Service
public class IndispEscolaExameService {
    @Autowired
    private IndispEscolaExameRepository indispEscolaExameRepository;

    public List<IndispEscolaExame> findAll(){
        return indispEscolaExameRepository.findAll();
    }

    public IndispEscolaExame findById(Integer id){
        return indispEscolaExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "indisp-sala-exame não encontrada"));
    }
}