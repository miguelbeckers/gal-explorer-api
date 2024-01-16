package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.PeriodConverter;
import pt.ipb.galconverterapi.model._new.Period;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeriodRepository {
    @Autowired
    private PeriodConverter periodConverter;

    private List<Period> periods = new ArrayList<>();

    public void load() {
        periods = periodConverter.convert();
    }

    public List<Period> findAll() {
        return periods;
    }
}
