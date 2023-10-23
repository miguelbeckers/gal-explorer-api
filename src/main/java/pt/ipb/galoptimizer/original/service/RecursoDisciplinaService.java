package pt.ipb.galoptimizer.original.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.original.model.RecursoDisciplina;
import pt.ipb.galoptimizer.original.repository.RecursoDisciplinaRepository;

import java.util.List;

@Service
public class RecursoDisciplinaService {
    @Autowired
    private RecursoDisciplinaRepository recursoDisciplinaRepository;

    public List<RecursoDisciplina> findAll(){
        return recursoDisciplinaRepository.findAll();
    }

    public RecursoDisciplina findById(Integer id){
        return recursoDisciplinaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recurso-diciplina não encontrado"));
    }
}
