package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.LessonResource;
import pt.ipb.galconverterapi.model.old.RecursoDisciplina;
import pt.ipb.galconverterapi.repository.old.RecursoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonResourceConverter {
    @Autowired
    private RecursoDisciplinaRepository recursoDisciplinaRepository;

    public List<LessonResource> convert() {
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<LessonResource> lessonResources = new ArrayList<>();

        for (RecursoDisciplina recursoDisciplina : recursoDisciplinas) {
            LessonResource lessonResource = new LessonResource();
            lessonResource.setId((long) recursoDisciplina.getId());
            lessonResource.setQuantity(recursoDisciplina.getQuantidade());
            lessonResource.setResource((long) recursoDisciplina.getIdRec());
            lessonResources.add(lessonResource);
        }

        return lessonResources;
    }
}
