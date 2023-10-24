package pt.ipb.galoptimizer.optimizer.repository;

import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.optimizer.model.Lesson;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonRepository {
    private final List<Lesson> lessons = new ArrayList<>();

    public List<Lesson> findAll() {
        return lessons;
    }

    public Lesson findById(Long id) {
        return lessons.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public Lesson save(Lesson item) {
        if (item.getId() == null) throw new IllegalArgumentException("Lesson id cannot be null");
        lessons.add(item);
        return item;
    }

    public void saveAll(List<Lesson> items) {
        lessons.addAll(items);
    }

    public void deleteById(Long id) {
        lessons.removeIf(item -> item.getId().equals(id));
    }
}
