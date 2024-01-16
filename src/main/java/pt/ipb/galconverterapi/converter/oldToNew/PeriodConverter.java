package pt.ipb.galconverterapi.converter.oldToNew;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Period;
import pt.ipb.galconverterapi.model.old.Ano;
import pt.ipb.galconverterapi.repository.old.AnoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeriodConverter {
    @Autowired
    private AnoRepository anoRepository;

    public List<Period> convert() {
        List<Ano> anos = anoRepository.findAll();
        List<Period> periods = new ArrayList<>();

        for(Ano ano : anos) {
            Period period = new Period();
            period.setId((long) ano.getId());
            period.setName(ano.getNome());

            period.setAbbreviation(ano.getAbrev());
            periods.add(period);
        }

        return periods;
    }
}
