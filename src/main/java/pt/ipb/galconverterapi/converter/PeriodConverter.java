package pt.ipb.galconverterapi.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.PeriodDto;
import pt.ipb.galconverterapi.model.Ano;
import pt.ipb.galconverterapi.repository.AnoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeriodConverter {
    private final AnoRepository anoRepository;

    @Autowired
    public PeriodConverter(AnoRepository anoRepository) {
        this.anoRepository = anoRepository;
    }

    public List<PeriodDto> convert() {
        List<Ano> anos = anoRepository.findAll();
        List<PeriodDto> periodDtos = new ArrayList<>();

        for (Ano ano : anos) {
            PeriodDto periodDto = new PeriodDto();
            periodDto.setId((long) ano.getId());
            periodDto.setName(ano.getNome());

            periodDto.setAbbreviation(ano.getAbrev());
            periodDtos.add(periodDto);
        }

        return periodDtos;
    }
}
