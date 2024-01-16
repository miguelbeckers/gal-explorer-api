package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Resource;
import pt.ipb.galconverterapi.model.old.Recurso;
import pt.ipb.galconverterapi.repository.old.RecursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceConverter {
    @Autowired
    private RecursoRepository recursoRepository;

    public List<Resource> convert() {
        List<Recurso> recursos = recursoRepository.findAll();
        List<Resource> resources = new ArrayList<>();

        for (Recurso recurso : recursos) {
            Resource resource = new Resource();
            resource.setId((long) recurso.getId());
            resource.setName(recurso.getNome());
            resource.setAbbreviation(recurso.getAbrev());
            resources.add(resource);
        }

        return resources;
    }
}
