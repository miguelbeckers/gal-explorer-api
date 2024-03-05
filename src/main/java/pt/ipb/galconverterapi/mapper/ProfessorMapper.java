package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ProfessorDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Docente;
import pt.ipb.galconverterapi.repository.DocenteRepository;
import pt.ipb.galconverterapi.repository.IndisponibilidadeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorMapper {
    private final DocenteRepository docenteRepository;
    private final IndisponibilidadeRepository indisponibilidadeRepository;
    private final TimeslotMapper timeslotMapper;

    @Autowired
    public ProfessorMapper(
            DocenteRepository docenteRepository,
            IndisponibilidadeRepository indisponibilidadeRepository,
            TimeslotMapper timeslotMapper) {
        this.docenteRepository = docenteRepository;
        this.indisponibilidadeRepository = indisponibilidadeRepository;
        this.timeslotMapper = timeslotMapper;
    }

    public List<ProfessorDto> map() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();

        List<ProfessorDto> professorDtos = new ArrayList<>();
        for (Docente docente : docentes) {
            ProfessorDto professorDto = new ProfessorDto();
            professorDto.setId((long) docente.getId());
            professorDto.setName(docente.getNome());
            professorDto.setAbbreviation(docente.getAbrev());
            professorDto.setDepartmentId((long) docente.getIdDepart());

            List<Object[]> indisponibilidadesProfessor = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("D") && idTipo == docente.getId()) {
                    indisponibilidadesProfessor.add(indisponibilidade);
                }
            }

            List<TimeslotDto> professorUnavailability = timeslotMapper.map(indisponibilidadesProfessor);
            professorDto.setUnavailabilityIds(professorUnavailability.stream().map(TimeslotDto::getId).toList());

            professorDtos.add(professorDto);
        }

        return professorDtos;
    }
}
