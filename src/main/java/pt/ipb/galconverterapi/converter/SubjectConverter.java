package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.SubjectDto;
import pt.ipb.galconverterapi.model.Disciplina;
import pt.ipb.galconverterapi.repository.DisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectConverter {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<SubjectDto> convert() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId((long) disciplina.getId());
            subjectDto.setName(disciplina.getNome());
            subjectDto.setCode(disciplina.getAbrev());
            subjectDtos.add(subjectDto);
        }

        return subjectDtos;
    }
}
