package pt.ipb.galoptimizer.original.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.original.model.TipoAulaDisciplina;
import pt.ipb.galoptimizer.original.repository.TipoAulaDisciplinaRepository;

import java.util.List;

@Service
public class TipoAulaDisciplinaService {
    @Autowired
    private TipoAulaDisciplinaRepository alunoDisciplinaRepository;

    public List<TipoAulaDisciplina> findAll(){
        return alunoDisciplinaRepository.findAll();
    }

    public TipoAulaDisciplina findById(Integer id){
        return alunoDisciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "tipo-aula-disciplina não encontrado"));
    }
}
