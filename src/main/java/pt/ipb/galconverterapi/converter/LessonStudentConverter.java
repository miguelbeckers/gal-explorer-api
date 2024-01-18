package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonStudentDto;
import pt.ipb.galconverterapi.model.AlunoDisciplina;
import pt.ipb.galconverterapi.repository.AlunoDisciplinaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonStudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    public List<LessonStudentDto> convert() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<LessonStudentDto> lessonStudentDtos = new ArrayList<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            LessonStudentDto lessonStudentDto = new LessonStudentDto();
            lessonStudentDto.setId((long) alunoDisciplina.getId());
            lessonStudentDto.setStudent((long) alunoDisciplina.getIdAluno());
            lessonStudentDtos.add(lessonStudentDto);
        }

        return lessonStudentDtos;
    }
}
