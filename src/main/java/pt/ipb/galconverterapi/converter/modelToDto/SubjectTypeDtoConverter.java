package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.SubjectTypeDto;
import pt.ipb.galconverterapi.model.TipoAula;
import pt.ipb.galconverterapi.repository.TipoAulaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectTypeDtoConverter {
    @Autowired
    private TipoAulaRepository tipoAulaRepository;

    public List<SubjectTypeDto> convert() {
        List<TipoAula> tipoAulas = tipoAulaRepository.findAll();
        List<SubjectTypeDto> subjectTypeDtos = new ArrayList<>();

        for (TipoAula tipoAula : tipoAulas) {
            SubjectTypeDto subjectTypeDto = new SubjectTypeDto();
            subjectTypeDto.setId((long) tipoAula.getId());
            subjectTypeDto.setName(tipoAula.getNome());
            subjectTypeDto.setAbbreviation(tipoAula.getAbrev());

            subjectTypeDto.setCor(tipoAula.getCor());
            subjectTypeDto.setPrioridade(tipoAula.getPrioridade());
            subjectTypeDtos.add(subjectTypeDto);
        }

        return subjectTypeDtos;
    }
}
