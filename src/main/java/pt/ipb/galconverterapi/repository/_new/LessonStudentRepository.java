package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.LessonStudentConverter;
import pt.ipb.galconverterapi.model._new.LessonStudent;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonStudentRepository {
    @Autowired
    private LessonStudentConverter lessonStudentConverter;

    private List<LessonStudent> lessonStudents = new ArrayList<>();

    public void load() {
        lessonStudents = lessonStudentConverter.convert();
    }

    public List<LessonStudent> findAll() {
        return lessonStudents;
    }
}
