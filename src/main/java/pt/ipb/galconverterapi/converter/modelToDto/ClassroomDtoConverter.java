package pt.ipb.galconverterapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.ClassroomDto;
import pt.ipb.galconverterapi.dto.ClassroomResourceDto;
import pt.ipb.galconverterapi.dto.TimeslotDto;
import pt.ipb.galconverterapi.model.Sala;
import pt.ipb.galconverterapi.repository.HorarioRepository;
import pt.ipb.galconverterapi.repository.SalaRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomDtoConverter {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private TimeslotDtoConverter timeslotDtoConverter;

    @Autowired
    private ClassroomResourceDtoConverter classroomResourceDtoConverter;

    public List<ClassroomDto> convert() {
        List<Sala> salas = salaRepository.findAll();
        List<ClassroomDto> classrooms = new ArrayList<>();

        List<TimeslotDto> timeslots = timeslotDtoConverter.convert();
        List<ClassroomResourceDto> classroomResources = classroomResourceDtoConverter.convert();

        List<Object[]> indisponibilidades = horarioRepository.findIndisponibilidades();

        for (Sala sala : salas) {
            ClassroomDto classroom = new ClassroomDto();
            classroom.setId((long) sala.getId());
            classroom.setName(sala.getNome());
            classroom.setAbbreviation(sala.getAbrev());

            //FIXME: filter not just by id but by type too
            List<Object[]> indisponibilidadesClassroom = indisponibilidades.stream()
                    .filter(indisponibilidade -> (int) indisponibilidade[1] == sala.getId())
                    .toList();

            List<TimeslotDto> classroomUnavailability = new ArrayList<>();
            for (Object[] indisponibilidade : indisponibilidadesClassroom) {
                classroomUnavailability.add(timeslots.stream()
                        .filter(timeslot ->
                                timeslot.getDayOfWeek().getValue() == (int) indisponibilidade[3]
                                        && timeslot.getStartTime().equals(indisponibilidade[4])
                                        && timeslot.getEndTime().equals(indisponibilidade[5]))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Timeslot (" +
                                "day[" + DayOfWeek.of((int) indisponibilidade[3]) + "]," +
                                "startTime[" + indisponibilidade[4] + "]," +
                                "startTime[" + indisponibilidade[5] + "]," +
                                "not found"))
                );
            }

            classroom.setUnavailability(classroomUnavailability);

            //TODO: finish implementing the resource association
//            List<ClassroomResourceDto> classroomClassroomResources = classroomResources.stream()
//                    .filter(classroomResource -> classroomResource.getId().equals())

            classrooms.add(classroom);
        }

        return new ArrayList<>();
    }
}
