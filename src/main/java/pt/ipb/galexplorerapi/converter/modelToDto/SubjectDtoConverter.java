package pt.ipb.galexplorerapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galexplorerapi.dto.SubjectDto;
import pt.ipb.galexplorerapi.model.Disciplina;
import pt.ipb.galexplorerapi.repository.DisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectDtoConverter {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public void convert() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();

        for(Disciplina disciplina : disciplinas) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId((long) disciplina.getId());
            subjectDto.setName(disciplina.getNome());
            subjectDto.setCode(disciplina.getAbrev());

            subjectDto.setIdDepart(disciplina.getIdDepart());
            subjectDto.setIpbCodEscola(disciplina.getIpbCodEscola());
            subjectDto.setIpbCodCurso(disciplina.getIpbCodCurso());
            subjectDto.setIpbNPlano(disciplina.getIpbNPlano());
            subjectDto.setIpbNDisciplina(disciplina.getIpbNDisciplina());
            subjectDto.setIpbNOpcao(disciplina.getIpbNOpcao());
            subjectDtos.add(subjectDto);
        }
    }
}
