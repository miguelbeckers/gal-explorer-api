package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.SubjectType;
import pt.ipb.galconverterapi.model.old.TipoAula;
import pt.ipb.galconverterapi.repository.old.TipoAulaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectTypeConverter {
    @Autowired
    private TipoAulaRepository tipoAulaRepository;

    public List<SubjectType> convert() {
        List<TipoAula> tipoAulas = tipoAulaRepository.findAll();
        List<SubjectType> subjectTypes = new ArrayList<>();

        for (TipoAula tipoAula : tipoAulas) {
            SubjectType subjectType = new SubjectType();
            subjectType.setId((long) tipoAula.getId());
            subjectType.setName(tipoAula.getNome());
            subjectType.setAbbreviation(tipoAula.getAbrev());

            subjectType.setCor(tipoAula.getCor());
            subjectType.setPrioridade(tipoAula.getPrioridade());
            subjectTypes.add(subjectType);
        }

        return subjectTypes;
    }
}
