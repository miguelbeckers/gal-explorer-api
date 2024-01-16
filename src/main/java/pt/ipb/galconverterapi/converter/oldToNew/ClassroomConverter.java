package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Classroom;
import pt.ipb.galconverterapi.model._new.ClassroomResource;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Sala;
import pt.ipb.galconverterapi.repository.old.SalaRepository;
import pt.ipb.galconverterapi.repository.old.HorarioRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomConverter {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @Autowired
    private ClassroomResourceConverter classroomResourceConverter;

    public List<Classroom> convert() {
        List<Sala> salas = salaRepository.findAll();
        List<Classroom> classrooms = new ArrayList<>();

        List<Timeslot> timeslots = timeslotConverter.convert();
        List<ClassroomResource> classroomResources = classroomResourceConverter.convert();

        List<Object[]> indisponibilidades = horarioRepository.findIndisponibilidades();

        for (Sala sala : salas) {
            Classroom classroom = new Classroom();
            classroom.setId((long) sala.getId());
            classroom.setName(sala.getNome());
            classroom.setAbbreviation(sala.getAbrev());

            //FIXME: filter not just by id but by type too
            List<Object[]> indisponibilidadesClassroom = indisponibilidades.stream()
                    .filter(indisponibilidade -> (int) indisponibilidade[1] == sala.getId())
                    .toList();

            List<Timeslot> classroomUnavailability = new ArrayList<>();
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
