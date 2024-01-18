package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.RecursoSala;
import pt.ipb.galconverterapi.model.Sala;
import pt.ipb.galconverterapi.repository.IndisponibilidadeRepository;
import pt.ipb.galconverterapi.repository.RecursoSalaRepository;
import pt.ipb.galconverterapi.repository.SalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomConverter {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    public List<ClassroomDto> convert() {
        List<Sala> salas = salaRepository.findAll();
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();

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

            List<RecursoSala> recursoSalasSala = recursoSalas.stream()
                    .filter(recursoSala -> recursoSala.getIdSala() == sala.getId()).toList();

            classroomDto.setClassroomResources(recursoSalasSala.stream()
                    .map(recursoSala -> (long) recursoSala.getId()).toList());

            classroomDtos.add(classroomDto);
        }

        return classroomDtos;
    }
}
