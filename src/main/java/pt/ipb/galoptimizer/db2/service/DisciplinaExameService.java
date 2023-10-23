package pt.ipb.galoptimizer.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.db2.model.DisciplinaExame;
import pt.ipb.galoptimizer.db2.repository.DisciplinaExameRepository;

import java.util.List;

@Service
public class DisciplinaExameService {
    @Autowired
    private DisciplinaExameRepository disciplinaExameRepository;

    public List<DisciplinaExame> findAll(){
        return disciplinaExameRepository.findAll();
    }

    public DisciplinaExame findById(Integer id){
        return disciplinaExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "disciplina-exame não encontrada"));
    }
}
