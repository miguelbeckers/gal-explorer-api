package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Lesson;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonConverter {

    public List<Lesson> convert() {
        return new ArrayList<>();
    }
}
