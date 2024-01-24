package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pt.ipb.galconverterapi.dto.ClassroomDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.RecursoSala;
import pt.ipb.galconverterapi.model.Sala;
import pt.ipb.galconverterapi.model.TipoSala;
import pt.ipb.galconverterapi.repository.IndisponibilidadeRepository;
import pt.ipb.galconverterapi.repository.RecursoSalaRepository;
import pt.ipb.galconverterapi.repository.SalaRepository;
import pt.ipb.galconverterapi.repository.TipoSalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomConverter {
    private final SalaRepository salaRepository;
    private final RecursoSalaRepository recursoSalaRepository;
    private final IndisponibilidadeRepository indisponibilidadeRepository;
    private final TipoSalaRepository tipoSalaRepository;
    private final TimeslotConverter timeslotConverter;

    @Autowired
    public ClassroomConverter(SalaRepository salaRepository,
                              RecursoSalaRepository recursoSalaRepository,
                              IndisponibilidadeRepository indisponibilidadeRepository,
                              TipoSalaRepository tipoSalaRepository,
                              TimeslotConverter timeslotConverter) {
        this.salaRepository = salaRepository;
        this.recursoSalaRepository = recursoSalaRepository;
        this.indisponibilidadeRepository = indisponibilidadeRepository;
        this.tipoSalaRepository = tipoSalaRepository;
        this.timeslotConverter = timeslotConverter;
    }

    public List<ClassroomDto> convert() {
        List<Sala> salas = salaRepository.findAll();
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();
        List<TipoSala> tipoSalas = tipoSalaRepository.findAll();
        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();

        List<ClassroomDto> classroomDtos = new ArrayList<>();
        for (Sala sala : salas) {
            ClassroomDto classroomDto = new ClassroomDto();
            classroomDto.setId((long) sala.getId());
            classroomDto.setName(sala.getNome());
            classroomDto.setAbbreviation(sala.getAbrev());

            List<Object[]> indisponibilidadesSala = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("S") && idTipo == sala.getId()) {
                    indisponibilidadesSala.add(indisponibilidade);
                }
            }

            List<TimeslotDto> professorUnavailability = timeslotConverter.convert(indisponibilidadesSala);
            classroomDto.setUnavailability(professorUnavailability.stream().map(TimeslotDto::getId).toList());

            classroomDto.setClassroomResources(recursoSalas.stream()
                    .filter(recursoSala -> recursoSala.getIdSala() == sala.getId())
                    .map(recursoSala -> (long) recursoSala.getId())
                    .toList());

            classroomDto.setClassroomTypeId(tipoSalas.stream()
                    .filter(tipoSala -> tipoSala.getId() == sala.getIdTipo())
                    .findFirst()
                    .map(tipoSala -> (long) tipoSala.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "TipoSala not found: " + sala.getId())));

            classroomDtos.add(classroomDto);
        }

        return classroomDtos;
    }
}
