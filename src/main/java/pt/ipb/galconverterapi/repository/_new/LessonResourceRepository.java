package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.LessonResourceConverter;
import pt.ipb.galconverterapi.model._new.LessonResource;

import java.util.List;

@Repository
public class LessonResourceRepository {
    @Autowired
    private LessonResourceConverter lessonResourceConverter;

    private List<LessonResource> lessonResources;

    public void load() {
        lessonResources = lessonResourceConverter.convert();
    }

    public List<LessonResource> findAll() {
        return lessonResources;
    }
}
