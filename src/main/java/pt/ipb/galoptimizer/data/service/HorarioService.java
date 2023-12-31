package pt.ipb.galoptimizer.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.data.model.Horario;
import pt.ipb.galoptimizer.data.repository.HorarioRepository;

import java.util.List;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> findAll(){
        return horarioRepository.findAll();
    }

    public Horario findById(Integer id){
        return horarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "horario não encontrado"));
    }
}
