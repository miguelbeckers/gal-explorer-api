package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomResourceDto;
import pt.ipb.galconverterapi.model.RecursoSala;
import pt.ipb.galconverterapi.repository.RecursoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomResourceConverter {
    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    public List<ClassroomResourceDto> convert() {
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        List<ClassroomResourceDto> classroomResourceDtos = new ArrayList<>();

        for (RecursoSala recursoSala : recursoSalas) {
            ClassroomResourceDto classroomResourceDto = new ClassroomResourceDto();
            classroomResourceDto.setId((long) recursoSala.getId());
            classroomResourceDto.setQuantity(recursoSala.getQuantidade());
            classroomResourceDto.setResource((long) recursoSala.getIdRec());
            classroomResourceDtos.add(classroomResourceDto);
        }

        return classroomResourceDtos;
    }
}
