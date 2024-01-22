package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galconverterapi.dto.StudentDto;
import pt.ipb.galconverterapi.model.AlunoDisciplina;
import pt.ipb.galconverterapi.model.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.AlunoDisciplinaRepository;
import pt.ipb.galconverterapi.repository.DisciplinaCursoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class StudentConverter {
    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    public List<StudentDto> convert() {
        return getStudentsWithoutSubjectCourses();
//        return getStudentsWithSubjectCourses();
    }

    public List<StudentDto> getStudentsWithoutSubjectCourses() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();

        HashMap<Long, StudentDto> studentMap = new HashMap<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Long id = (long) alunoDisciplina.getIdAluno();

            if (!studentMap.containsKey(id)) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(id);
                studentDto.setSubjectCourseIds(List.of());
                studentMap.put(id, studentDto);
            }
        }

        return new ArrayList<>(studentMap.values());
    }

    private List<StudentDto> getStudentsWithSubjectCourses() {
        List<AlunoDisciplina> alunoDisciplinas = alunoDisciplinaRepository.findAll();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();

        HashMap<Long, StudentDto> studentMap = new HashMap<>();

        for (AlunoDisciplina alunoDisciplina : alunoDisciplinas) {
            Long id = (long) alunoDisciplina.getIdAluno();

            DisciplinaCurso disciplinaCurso = disciplinaCursos.stream()
                    .filter(dc -> dc.getIdCurso() == alunoDisciplina.getIdCurso()
                            && dc.getIdDiscip() == alunoDisciplina.getIdDiscip()
                            && dc.getIdAno() == alunoDisciplina.getAno())
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "DisciplinaCurso not found for alunoDisciplina: " + alunoDisciplina.getId()
                                    + " with idCurso: " + alunoDisciplina.getIdCurso()
                                    + " idDiscip: " + alunoDisciplina.getIdDiscip()
                                    + " idAno: " + alunoDisciplina.getAno()
                    ));

            if (!studentMap.containsKey(id)) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(id);
                studentDto.setSubjectCourseIds(List.of((long) disciplinaCurso.getId()));
                studentMap.put(id, studentDto);
            } else {
                StudentDto studentDto = studentMap.get(id);
                List<Long> subjectCourses = new ArrayList<>(studentDto.getSubjectCourseIds());
                subjectCourses.add((long) disciplinaCurso.getId());
                studentDto.setSubjectCourseIds(subjectCourses);
            }
        }

        return new ArrayList<>(studentMap.values());
    }
}
