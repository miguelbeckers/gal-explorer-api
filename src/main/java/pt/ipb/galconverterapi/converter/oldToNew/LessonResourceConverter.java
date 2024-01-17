package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.LessonResource;
import pt.ipb.galconverterapi.model._new.Resource;
import pt.ipb.galconverterapi.model.old.RecursoDisciplina;
import pt.ipb.galconverterapi.repository._new.ResourceRepository;
import pt.ipb.galconverterapi.repository.old.RecursoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonResourceConverter {
    @Autowired
    private RecursoDisciplinaRepository recursoDisciplinaRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public List<LessonResource> convert(){
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<LessonResource> lessonResources = new ArrayList<>();

        List<Resource> resources = resourceRepository.findAll();

        for(RecursoDisciplina recursoDisciplina : recursoDisciplinas){
            LessonResource lessonResource = new LessonResource();
            lessonResource.setId((long)recursoDisciplina.getId());
            lessonResource.setQuantity(recursoDisciplina.getQuantidade());

            Resource resource = resources.stream()
                    .filter(r -> r.getId().equals((long)recursoDisciplina.getIdRec()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Resource id: " + recursoDisciplina.getIdRec() + " not found"));

            lessonResource.setResource(resource.getId());
            lessonResources.add(lessonResource);
        }

        return lessonResources;
    }
}
