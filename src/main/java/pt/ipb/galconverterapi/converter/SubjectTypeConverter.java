package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.SubjectTypeDto;
import pt.ipb.galconverterapi.model.TipoAula;
import pt.ipb.galconverterapi.repository.TipoAulaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectTypeConverter {
    private final TipoAulaRepository tipoAulaRepository;

    @Autowired
    public SubjectTypeConverter(TipoAulaRepository tipoAulaRepository) {
        this.tipoAulaRepository = tipoAulaRepository;
    }

    public List<SubjectTypeDto> convert() {
        List<TipoAula> tipoAulas = tipoAulaRepository.findAll();
        List<SubjectTypeDto> subjectTypeDtos = new ArrayList<>();

        for (TipoAula tipoAula : tipoAulas) {
            SubjectTypeDto subjectTypeDto = new SubjectTypeDto();
            subjectTypeDto.setId((long) tipoAula.getId());
            subjectTypeDto.setName(tipoAula.getNome());
            subjectTypeDto.setAbbreviation(tipoAula.getAbrev());
            subjectTypeDtos.add(subjectTypeDto);
        }

        return subjectTypeDtos;
    }
}
