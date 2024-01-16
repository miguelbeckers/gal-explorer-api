package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.SubjectConverter;
import pt.ipb.galconverterapi.model._new.Subject;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectRepository {
    @Autowired
    private SubjectConverter subjectConverter;

    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> findAll() {
        return subjects;
    }

    public void load() {
        subjects = subjectConverter.convert();
    }
}
