package pt.ipb.galoptimizer.optimizer.repository;

import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.optimizer.model.Classroom;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassroomRepository {
    private final List<Classroom> classrooms = new ArrayList<>();

    public List<Classroom> findAll() {
        return classrooms;
    }

    public Classroom findById(Long id) {
        return classrooms.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public Classroom save(Classroom item) {
        if (item.getId() == null) throw new IllegalArgumentException("Classroom id cannot be null");
        classrooms.add(item);
        return item;
    }

    public void deleteById(Long id) {
        classrooms.removeIf(item -> item.getId().equals(id));
    }
}
