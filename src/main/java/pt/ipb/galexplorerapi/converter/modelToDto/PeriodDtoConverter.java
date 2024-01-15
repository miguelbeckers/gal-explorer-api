package pt.ipb.galexplorerapi.converter.modelToDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galexplorerapi.dto.PeriodDto;
import pt.ipb.galexplorerapi.model.Ano;
import pt.ipb.galexplorerapi.repository.AnoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeriodDtoConverter {
    @Autowired
    private AnoRepository anoRepository;


    public void convert() {
        List<Ano> anos = anoRepository.findAll();
        List<PeriodDto> periodDtos = new ArrayList<>();

        for(Ano ano : anos) {
            PeriodDto periodDto = new PeriodDto();
            periodDto.setId((long) ano.getId());
            periodDto.setName(ano.getNome());

            periodDto.setAbbreviation(ano.getAbrev());
            periodDtos.add(periodDto);
        }
    }
}
