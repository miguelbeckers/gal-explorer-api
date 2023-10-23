package pt.ipb.galoptimizer.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.db2.model.IndispDocenteExame;
import pt.ipb.galoptimizer.db2.repository.IndispDocenteExameRepository;

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
