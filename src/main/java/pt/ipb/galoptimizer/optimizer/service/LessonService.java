package pt.ipb.galoptimizer.optimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipb.galoptimizer.optimizer.model.Lesson;
import pt.ipb.galoptimizer.optimizer.repository.LessonRepository;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository classroomRepository;

    public List<Lesson> findAll() {
        return classroomRepository.findAll();
    }

    public Lesson update(Lesson lesson) {
        return classroomRepository.save(lesson);
    }
}
