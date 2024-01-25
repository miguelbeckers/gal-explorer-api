package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonResourceDto;
import pt.ipb.galconverterapi.model.RecursoDisciplina;
import pt.ipb.galconverterapi.repository.RecursoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonResourceMapper {
    private final RecursoDisciplinaRepository recursoDisciplinaRepository;

    @Autowired
    public LessonResourceMapper(RecursoDisciplinaRepository recursoDisciplinaRepository) {
        this.recursoDisciplinaRepository = recursoDisciplinaRepository;
    }

    public List<LessonResourceDto> convert() {
        List<RecursoDisciplina> recursoDisciplinas = recursoDisciplinaRepository.findAll();
        List<LessonResourceDto> lessonResourceDtos = new ArrayList<>();

        for (RecursoDisciplina recursoDisciplina : recursoDisciplinas) {
            LessonResourceDto lessonResourceDto = new LessonResourceDto();
            lessonResourceDto.setId((long) recursoDisciplina.getId());
            lessonResourceDto.setQuantity(recursoDisciplina.getQuantidade());
            lessonResourceDto.setResourceId((long) recursoDisciplina.getIdRec());
            lessonResourceDtos.add(lessonResourceDto);
        }

        return lessonResourceDtos;
    }
}
