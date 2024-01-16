package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Subject;
import pt.ipb.galconverterapi.model.old.Disciplina;
import pt.ipb.galconverterapi.repository.old.DisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectConverter {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Subject> convert() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        List<Subject> subjects = new ArrayList<>();

        for(Disciplina disciplina : disciplinas) {
            Subject subject = new Subject();
            subject.setId((long) disciplina.getId());
            subject.setName(disciplina.getNome());
            subject.setCode(disciplina.getAbrev());

            subject.setIdDepart(disciplina.getIdDepart());
            subject.setIpbCodEscola(disciplina.getIpbCodEscola());
            subject.setIpbCodCurso(disciplina.getIpbCodCurso());
            subject.setIpbNPlano(disciplina.getIpbNPlano());
            subject.setIpbNDisciplina(disciplina.getIpbNDisciplina());
            subject.setIpbNOpcao(disciplina.getIpbNOpcao());
            subjects.add(subject);
        }

        return subjects;
    }
}
