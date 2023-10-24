package pt.ipb.galoptimizer.optimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipb.galoptimizer.optimizer.model.Classroom;
import pt.ipb.galoptimizer.optimizer.repository.ClassroomRepository;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
}
