package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.AlunoDisciplina;
import pt.ipb.galoptimizer.repository.AlunoDisciplinaRepository;

import java.util.List;

@Service
public class RecursoSalaService {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<AlunoDisciplina> findAll(){
        return alunoDisciplinaRepository.findAll();
    }

    public AlunoDisciplina findById(Integer id){
        return alunoDisciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recurso-sala não encontrado"));
    }
}
