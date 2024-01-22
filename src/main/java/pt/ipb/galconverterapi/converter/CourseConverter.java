package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.CourseDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.AnoCurso;
import pt.ipb.galconverterapi.model.Curso;
import pt.ipb.galconverterapi.model.Disciplina;
import pt.ipb.galconverterapi.model.DisciplinaCurso;
import pt.ipb.galconverterapi.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CourseConverter {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private AnoCursoRepository anoCursoRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    public List<CourseDto> convert() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();

        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();
        List<DisciplinaCurso> disciplinaCursos = disciplinaCursoRepository.findAll();
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        List<AnoCurso> anoCursos = anoCursoRepository.findAll();

        HashMap<Integer, Disciplina> disciplinaHashMap = new HashMap<>();
        for (Disciplina disciplina : disciplinas) {
            disciplinaHashMap.put(disciplina.getId(), disciplina);
        }

        for (Curso curso : cursos) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId((long) curso.getId());
            courseDto.setName(curso.getNome());
            courseDto.setAbbreviation(curso.getAbrev());

            DisciplinaCurso disciplinaCurso = disciplinaCursos.stream()
                    .filter(dc -> dc.getIdCurso() == curso.getId())
                    .findFirst()
                    .orElse(null);

            if (disciplinaCurso != null) {
                Disciplina disciplina = disciplinaHashMap.get(disciplinaCurso.getIdDiscip());
                courseDto.setDepartmentId((long) disciplina.getIdDepart());
            }

            List<Object[]> indisponibilidadesCurso = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("C") && idTipo == curso.getId()) {
                    indisponibilidadesCurso.add(indisponibilidade);
                }
            }

            List<TimeslotDto> courseUnavailability = timeslotConverter.convert(indisponibilidadesCurso);
            courseDto.setUnavailabilityIds(courseUnavailability.stream().map(TimeslotDto::getId).toList());

            List<Long> anoCursosCurso = anoCursos.stream()
                    .filter(anoCurso -> anoCurso.getIdCurso() == curso.getId())
                    .map(anoCurso -> (long) anoCurso.getIdAno())
                    .toList();

            courseDto.setCoursePeriodIds(anoCursosCurso);
            courseDtos.add(courseDto);
        }

        return courseDtos;
    }
}
