package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.MarcacaoExame;
import pt.ipb.galoptimizer.repository.MarcacaoExameRepository;

import java.util.List;

@Service
public class MarcacaoExameService {
    @Autowired
    private MarcacaoExameRepository marcacaoExameRepository;

    public List<MarcacaoExame> findAll(){
        return marcacaoExameRepository.findAll();
    }

    public MarcacaoExame findById(Integer id){
        return marcacaoExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "marcacao-exame não encontrada"));
    }
}