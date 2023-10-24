package pt.ipb.galoptimizer.optimizer.repository;

import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.optimizer.model.Timeslot;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeslotRepository {
    private final List<Timeslot> timeslots = new ArrayList<>();

    public List<Timeslot> findAll() {
        return timeslots;
    }

    public Timeslot findById(Long id) {
        return timeslots.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public Timeslot save(Timeslot item) {
        if (item.getId() == null) throw new IllegalArgumentException("Timeslot id cannot be null");
        timeslots.add(item);
        return item;
    }

    public void deleteById(Long id) {
        timeslots.removeIf(item -> item.getId().equals(id));
    }
}
