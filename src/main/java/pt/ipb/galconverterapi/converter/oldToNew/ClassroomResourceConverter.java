package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.ClassroomResource;
import pt.ipb.galconverterapi.model._new.Resource;
import pt.ipb.galconverterapi.model.old.RecursoSala;
import pt.ipb.galconverterapi.repository._new.ResourceRepository;
import pt.ipb.galconverterapi.repository.old.RecursoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomResourceConverter {
    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public List<ClassroomResource> convert() {
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();

        List<ClassroomResource> classroomResources = new ArrayList<>();

        for (RecursoSala recursoSala : recursoSalas) {
            ClassroomResource classroomResource = new ClassroomResource();
            classroomResource.setId((long) recursoSala.getId());
            classroomResource.setQuantity(recursoSala.getQuantidade());

            classroomResource.setResource(resources.stream()
                    .filter(resource -> resource.getId().equals((long)recursoSala.getIdRec()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Resource id: " + recursoSala.getIdRec() + " not found"))
            );

            classroomResources.add(classroomResource);
        }

        return classroomResources;
    }
}
