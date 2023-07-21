package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.IndispDocenteExame;
import pt.ipb.galoptimizer.repository.IndispDocenteExameRepository;

import java.util.List;

@Service
public class IndispDocenteExameService {
    @Autowired
    private IndispDocenteExameRepository indispDocenteExameRepository;

    public List<IndispDocenteExame> findAll(){
        return indispDocenteExameRepository.findAll();
    }

    public IndispDocenteExame findById(Integer id){
        return indispDocenteExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "indisp-docente-exame não encontrada"));
    }
}
