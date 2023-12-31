package pt.ipb.galoptimizer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.data.model.AulaDocente;
import pt.ipb.galoptimizer.data.repository.AulaDocenteRepository;

import java.util.List;

@Service
public class AulaDocenteService {
    @Autowired
    private AulaDocenteRepository aulaDocenteRepository;

    public List<AulaDocente> findAll(){
        return aulaDocenteRepository.findAll();
    }

    public AulaDocente findById(Integer id){
        return aulaDocenteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "aula-docente não encontrada"));
    }
}
