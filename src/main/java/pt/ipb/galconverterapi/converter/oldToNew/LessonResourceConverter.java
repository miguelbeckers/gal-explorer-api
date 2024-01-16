package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.LessonResource;
import pt.ipb.galconverterapi.model._new.Resource;
import pt.ipb.galconverterapi.model.old.RecursoDisciplina;
import pt.ipb.galconverterapi.repository.old.RecursoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonResourceConverter {
    @Autowired
    private RecursoDisciplinaRepository recursoDisciplinaRepository;

    @Autowired
    private ResourceConverter resourceConverter;

    public List<LessonResource> convert(){
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<LessonResource> lessonResources = new ArrayList<>();

        List<Resource> resources = resourceConverter.convert();

        for(RecursoDisciplina recursoDisciplina : recursoDisciplinas){
            LessonResource lessonResource = new LessonResource();
            lessonResource.setId((long)recursoDisciplina.getId());
            lessonResource.setQuantity(recursoDisciplina.getQuantidade());

            lessonResource.setIdCurso(recursoDisciplina.getIdCurso());
            lessonResource.setIdAno(recursoDisciplina.getIdAno());
            lessonResource.setIdDiscip(recursoDisciplina.getIdDiscip());
            lessonResource.setIdTipoAula(recursoDisciplina.getIdTipoAula());
            lessonResource.setValorExacto(recursoDisciplina.getValorExacto());

            lessonResource.setResource(resources.stream()
                    .filter(resource -> resource.getId().equals((long)recursoDisciplina.getIdRec()))
                    .findFirst()
                    .orElseThrow(
                            () -> new RuntimeException("LessonResource [" + lessonResource.getId() + "] resource not found"))
                    );

            lessonResources.add(lessonResource);
        }

        return lessonResources;
    }
}
