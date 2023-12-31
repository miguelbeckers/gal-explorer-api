package pt.ipb.galoptimizer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.data.model.IndispSalaExame;
import pt.ipb.galoptimizer.data.repository.IndispSalaExameRepository;

import java.util.List;

@Service
public class IndispSalaExameService {
    @Autowired
    private IndispSalaExameRepository indispSalaExameRepository;

    public List<IndispSalaExame> findAll(){
        return indispSalaExameRepository.findAll();
    }

    public IndispSalaExame findById(Integer id){
        return indispSalaExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "indisp-sala-exame não encontrada"));
    }
}
