package pt.ipb.galoptimizer.optimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipb.galoptimizer.optimizer.model.Timeslot;
import pt.ipb.galoptimizer.optimizer.repository.TimeslotRepository;

import java.util.List;

@Service
public class TimeslotService {
    @Autowired
    private TimeslotRepository classroomRepository;

    public List<Timeslot> findAll() {
        return classroomRepository.findAll();
    }
}
