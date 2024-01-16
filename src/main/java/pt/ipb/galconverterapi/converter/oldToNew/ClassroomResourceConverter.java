package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.ClassroomResource;
import pt.ipb.galconverterapi.model._new.Resource;
import pt.ipb.galconverterapi.model.old.RecursoSala;
import pt.ipb.galconverterapi.repository.old.RecursoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomResourceConverter {
    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private ResourceConverter resourceConverter;

    public List<ClassroomResource> convert() {
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        //TODO: evaluate the usage of converter instead of repository
        List<Resource> resources = resourceConverter.convert();

        List<ClassroomResource> classroomResources = new ArrayList<>();
        for (RecursoSala recursoSala : recursoSalas) {
            ClassroomResource classroomResource = new ClassroomResource();
            classroomResource.setId((long) recursoSala.getId());
            classroomResource.setQuantity(recursoSala.getQuantidade());
            //TODO: change the exception method to http not found
            classroomResource.setResource(
                    resources.stream()
                            .filter(resource -> resource.getId() == recursoSala.getId())
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(
                                    "Resource id: " + recursoSala.getId() + " not found"))
            );

            classroomResources.add(classroomResource);
        }

        return classroomResources;
    }
}
