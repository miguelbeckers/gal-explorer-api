package pt.ipb.galoptimizer.schema1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.schema1.model.AlunoDisciplina;
import pt.ipb.galoptimizer.schema1.repository.AlunoDisciplinaRepository;

import java.util.List;

@Service
public class AlunoDisciplinaService {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<AlunoDisciplina> findAll(){
        return alunoDisciplinaRepository.findAll();
    }

    public AlunoDisciplina findById(Integer id){
        return alunoDisciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "aluno-diciplina não encontrado"));
    }
}
