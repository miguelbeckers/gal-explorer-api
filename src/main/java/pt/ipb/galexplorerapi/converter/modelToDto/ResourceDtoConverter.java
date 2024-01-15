package pt.ipb.galexplorerapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galexplorerapi.dto.ResourceDto;
import pt.ipb.galexplorerapi.model.Recurso;
import pt.ipb.galexplorerapi.repository.RecursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceDtoConverter {
    @Autowired
    private RecursoRepository recursoRepository;

    public void convert() {
        List<Recurso> recursos = recursoRepository.findAll();
        List<ResourceDto> resourceDtos = new ArrayList<>();

        for (Recurso recurso : recursos) {
            ResourceDto resourceDto = new ResourceDto();
            resourceDto.setId((long) recurso.getId());
            resourceDto.setName(recurso.getNome());
            resourceDto.setAbbreviation(recurso.getAbrev());
            resourceDtos.add(resourceDto);
        }
    }
}
