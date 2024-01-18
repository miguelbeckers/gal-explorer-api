package pt.ipb.galconverterapi.converter;

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
public class ProfessorConverter {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    public List<ProfessorDto> convert() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();

        List<ProfessorDto> professorDtos = new ArrayList<>();
        for (Docente docente : docentes) {
            ProfessorDto professorDto = new ProfessorDto();
            professorDto.setId((long) docente.getId());
            professorDto.setName(docente.getNome());
            professorDto.setAbbreviation(docente.getAbrev());
            professorDto.setDepartment((long) docente.getIdDepart());

            List<Object[]> indisponibilidadesProfessor = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("D") && idTipo == docente.getId()) {
                    indisponibilidadesProfessor.add(indisponibilidade);
                }
            }

            List<TimeslotDto> professorUnavailability = timeslotConverter.convert(indisponibilidadesProfessor);
            professorDto.setUnavailability(professorUnavailability.stream().map(TimeslotDto::getId).toList());

            professorDtos.add(professorDto);
        }

        return professorDtos;
    }
}
