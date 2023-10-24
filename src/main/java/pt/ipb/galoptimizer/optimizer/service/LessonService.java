package pt.ipb.galoptimizer.optimizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipb.galoptimizer.data.model.Disciplina;
import pt.ipb.galoptimizer.data.repository.DisciplinaRepository;
import pt.ipb.galoptimizer.optimizer.model.Lesson;
import pt.ipb.galoptimizer.optimizer.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void loadAll(){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();

        List<Lesson> lessons = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            Lesson lesson = new Lesson();

            lesson.setId(Integer.toUnsignedLong(disciplina.getId()));
            lesson.setSubject(disciplina.getNome());

            lesson.setTeacher("teacher");
            lesson.setStudentGroup("studentGroup");
            lesson.setColor("color");
            lesson.setGroupSize(30);
            lessons.add(lesson);
        }

        lessonRepository.saveAll(lessons);
    }
}
