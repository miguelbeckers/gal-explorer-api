package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ResourceDto;
import pt.ipb.galconverterapi.model.Recurso;
import pt.ipb.galconverterapi.repository.RecursoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceMapper {
    private final RecursoRepository recursoRepository;

    @Autowired
    public ResourceMapper(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    public List<ResourceDto> map() {
        List<Recurso> recursos = recursoRepository.findAll();
        List<ResourceDto> resourceDtos = new ArrayList<>();

        for (Recurso recurso : recursos) {
            ResourceDto resourceDto = new ResourceDto();
            resourceDto.setId((long) recurso.getId());
            resourceDto.setName(recurso.getNome());
            resourceDto.setAbbreviation(recurso.getAbrev());
            resourceDtos.add(resourceDto);
        }

        return resourceDtos;
    }
}
