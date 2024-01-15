package pt.ipb.galexplorerapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galexplorerapi.dto.SubjectTypeDto;
import pt.ipb.galexplorerapi.model.TipoAula;
import pt.ipb.galexplorerapi.repository.TipoAulaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectTypeDtoConverter {
    @Autowired
    private TipoAulaRepository tipoAulaRepository;

    public void convert() {
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
    }
}
