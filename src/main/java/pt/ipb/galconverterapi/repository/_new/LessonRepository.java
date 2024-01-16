package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.LessonConverter;
import pt.ipb.galconverterapi.model._new.Lesson;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonRepository {
    @Autowired
    private LessonConverter lessonConverter;

    private List<Lesson> lessons = new ArrayList<>();

    public void load() {
        lessons = lessonConverter.convert();
    }

    public List<Lesson> findAll() {
        return lessons;
    }
}
