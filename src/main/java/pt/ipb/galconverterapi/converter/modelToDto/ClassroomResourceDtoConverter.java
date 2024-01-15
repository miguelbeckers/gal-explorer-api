package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomResourceDto;
import pt.ipb.galconverterapi.dto.ResourceDto;
import pt.ipb.galconverterapi.model.RecursoSala;
import pt.ipb.galconverterapi.repository.RecursoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomResourceDtoConverter {
    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private ResourceDtoConverter resourceDtoConverter;

    public List<ClassroomResourceDto> convert() {
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        //TODO: evaluate the usage of converter instead of repository
        List<ResourceDto> resourceDtos = resourceDtoConverter.convert();

        List<ClassroomResourceDto> classroomResourceDtos = new ArrayList<>();
        for (RecursoSala recursoSala : recursoSalas) {
            ClassroomResourceDto classroomResourceDto = new ClassroomResourceDto();
            classroomResourceDto.setId((long) recursoSala.getId());
            classroomResourceDto.setQuantity(recursoSala.getQuantidade());
            //TODO: change the exception method to http not found
            classroomResourceDto.setResourceDto(
                    resourceDtos.stream()
                            .filter(resource -> resource.getId() == recursoSala.getId())
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(
                                    "Resource id: " + recursoSala.getId() + " not found"))
            );

            classroomResourceDtos.add(classroomResourceDto);
        }

        return classroomResourceDtos;
    }
}
