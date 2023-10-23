package pt.ipb.galoptimizer.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.db2.model.Dia;
import pt.ipb.galoptimizer.db2.repository.DiaRepository;

import java.util.List;

@Service
public class DiaService {
    @Autowired
    private DiaRepository diaRepository;

    public List<Dia> findAll(){
        return diaRepository.findAll();
    }

    public Dia findById(Integer id){
        return diaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "dia não encontrado"));
    }
}
