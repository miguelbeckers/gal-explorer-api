package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.Ano;
import pt.ipb.galoptimizer.repository.AnoRepository;

import java.util.List;

@Service
public class AnoService {
    @Autowired
    private AnoRepository anoRepository;

    public List<Ano> findAll(){
        return anoRepository.findAll();
    }

    public Ano findById(Integer id){
        return anoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ano não encontrado"));
    }
}
