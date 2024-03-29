package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomTypeDto;
import pt.ipb.galconverterapi.model.TipoSala;
import pt.ipb.galconverterapi.repository.TipoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomTypeMapper {
    private final TipoSalaRepository tipoSalaRepository;

    @Autowired
    public ClassroomTypeMapper(TipoSalaRepository tipoSalaRepository) {
        this.tipoSalaRepository = tipoSalaRepository;
    }

    public List<ClassroomTypeDto> map() {
        List<TipoSala> tipoSalas = tipoSalaRepository.findAll();

        List<ClassroomTypeDto> classroomTypeDtos = new ArrayList<>();
        for (TipoSala tipoSala : tipoSalas) {
            ClassroomTypeDto classroomTypeDto = new ClassroomTypeDto();
            classroomTypeDto.setId((long) tipoSala.getId());
            classroomTypeDto.setName(tipoSala.getNome());
            classroomTypeDtos.add(classroomTypeDto);
        }

        return classroomTypeDtos;
    }
}
