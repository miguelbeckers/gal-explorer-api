package pt.ipb.galoptimizer.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.db2.model.DocenteExame;
import pt.ipb.galoptimizer.db2.repository.DocenteExameRepository;

import java.util.List;

@Service
public class DocenteExameService {
    @Autowired
    private DocenteExameRepository docenteExameRepository;

    public List<DocenteExame> findAll(){
        return docenteExameRepository.findAll();
    }

    public DocenteExame findById(Integer id){
        return docenteExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "docente-exame não encontrado"));
    }
}
