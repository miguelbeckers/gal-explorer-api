package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.ProfessorConverter;
import pt.ipb.galconverterapi.model._new.Professor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {
    @Autowired
    private ProfessorConverter professorConverter;

    private List<Professor> professors = new ArrayList<>();

    public void load() {
        professors = professorConverter.convert();
    }

    public List<Professor> findAll() {
        return professors;
    }

    public Professor findById(Long id) {
        return professors.stream()
                .filter(professor -> professor.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Professor with id " + id + " not found!")
                );
    }
}
