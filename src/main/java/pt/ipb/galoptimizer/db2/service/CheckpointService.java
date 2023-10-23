package pt.ipb.galoptimizer.db2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galoptimizer.db2.model.Checkpoint;
import pt.ipb.galoptimizer.db2.repository.CheckpointRepository;

import java.util.List;

@Service
public class CheckpointService {
    @Autowired
    private CheckpointRepository checkpointRepository;

    public List<Checkpoint> findAll(){
        return checkpointRepository.findAll();
    }

    public Checkpoint findById(Integer id){
        return checkpointRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "checkpoint não encontrado"));
    }
}
