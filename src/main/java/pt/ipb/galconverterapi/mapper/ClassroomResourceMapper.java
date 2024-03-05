package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomResourceDto;
import pt.ipb.galconverterapi.model.RecursoSala;
import pt.ipb.galconverterapi.repository.RecursoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomResourceMapper {
    private final RecursoSalaRepository recursoSalaRepository;

    @Autowired
    public ClassroomResourceMapper(RecursoSalaRepository recursoSalaRepository) {
        this.recursoSalaRepository = recursoSalaRepository;
    }

    public List<ClassroomResourceDto> map() {
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        List<ClassroomResourceDto> classroomResourceDtos = new ArrayList<>();

        for (RecursoSala recursoSala : recursoSalas) {
            ClassroomResourceDto classroomResourceDto = new ClassroomResourceDto();
            classroomResourceDto.setId((long) recursoSala.getId());
            classroomResourceDto.setQuantity(recursoSala.getQuantidade());
            classroomResourceDto.setResourceId((long) recursoSala.getIdRec());
            classroomResourceDtos.add(classroomResourceDto);
        }

        return classroomResourceDtos;
    }
}
