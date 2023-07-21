package pt.ipb.galoptimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.model.IndispCursoExame;
import pt.ipb.galoptimizer.repository.IndispCursoExameRepository;

import java.util.List;

@Service
public class IndispCursoExameService {
    @Autowired
    private IndispCursoExameRepository indispCursoExameRepository;

    public List<IndispCursoExame> findAll(){
        return indispCursoExameRepository.findAll();
    }

    public IndispCursoExame findById(Integer id){
        return indispCursoExameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "indisp-curso-exame não encontrada"));
    }
}
