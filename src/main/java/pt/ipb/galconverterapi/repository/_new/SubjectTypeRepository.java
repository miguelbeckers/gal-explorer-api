package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.SubjectConverter;
import pt.ipb.galconverterapi.converter.oldToNew.SubjectTypeConverter;
import pt.ipb.galconverterapi.model._new.SubjectType;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectTypeRepository {
    @Autowired
    private SubjectTypeConverter subjectTypeConverter;

    private List<SubjectType> subjectTypes = new ArrayList<>();

    public void load() {
        subjectTypes = subjectTypeConverter.convert();
    }

    public List<SubjectType> findAll() {
        return subjectTypes;
    }
}
