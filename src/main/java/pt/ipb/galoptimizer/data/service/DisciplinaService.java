package pt.ipb.galoptimizer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.data.model.Disciplina;
import pt.ipb.galoptimizer.data.repository.DisciplinaRepository;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> findAll(){
        return disciplinaRepository.findAll();
    }

    public Disciplina findById(Integer id){
        return disciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "disciplina não encontrada"));
    }
}
