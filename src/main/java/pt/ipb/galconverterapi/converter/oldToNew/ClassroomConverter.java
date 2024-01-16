package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Classroom;
import pt.ipb.galconverterapi.model._new.ClassroomResource;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.*;
import pt.ipb.galconverterapi.repository._new.ClassroomResourceRepository;
import pt.ipb.galconverterapi.repository._new.TimeslotRepository;
import pt.ipb.galconverterapi.repository.old.HorarioRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;
import pt.ipb.galconverterapi.repository.old.RecursoSalaRepository;
import pt.ipb.galconverterapi.repository.old.SalaRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomConverter {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private ClassroomResourceRepository classroomResourceRepository;

    public List<Classroom> convert() {
        List<Sala> salas = salaRepository.findAll();

        List<Indisponibilidade> indisponibilidades = indisponibilidadeRepository.findAll();
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();

        List<Timeslot> timeslots = timeslotRepository.findAll();
        List<ClassroomResource> classroomResources = classroomResourceRepository.findAll();

        List<Classroom> classrooms = new ArrayList<>();
        for (Sala sala : salas) {
            Classroom classroom = new Classroom();
            classroom.setId((long) sala.getId());
            classroom.setName(sala.getNome());
            classroom.setAbbreviation(sala.getAbrev());

            List<Indisponibilidade> indisponibilidadesSala = indisponibilidades.stream()
                    .filter(indisponibilidade -> indisponibilidade.getIdTipo() == sala.getId()
                            && indisponibilidade.getTipo().equals("S"))
                    .toList();

            List<Timeslot> classroomUnavailability = new ArrayList<>();
            for (Indisponibilidade indisponibilidade : indisponibilidadesSala) {
                Timeslot timeslot = timeslots.stream()
                        .filter(t -> t.getDayOfWeek().equals(DayOfWeek.of(indisponibilidade.getIdDia())))
                        .filter(t -> t.getStartTime().equals(indisponibilidade.getInicio().toLocalTime()))
                        .filter(t -> t.getEndTime().equals(indisponibilidade.getFim().toLocalTime()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException("Timeslot [" + indisponibilidade.getIdDia() +
                                        ", " + indisponibilidade.getInicio() +
                                        ", " + indisponibilidade.getFim() + "] not found")
                        );

                classroomUnavailability.add(timeslot);
            }

            classroom.setUnavailability(classroomUnavailability);

            List<RecursoSala> recursoSalasSala = recursoSalas.stream()
                    .filter(recursoSala -> recursoSala.getIdSala() == sala.getId())
                    .toList();

            List<ClassroomResource> ClassroomClassroomResources = new ArrayList<>();
            for (RecursoSala recursoSala : recursoSalasSala) {
                ClassroomResource classroomResource = classroomResources.stream()
                        .filter(r -> r.getId().equals((long)recursoSala.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException("ClassroomResource [" + recursoSala.getId() + "] not found")
                        );

                ClassroomClassroomResources.add(classroomResource);
            }

            classroom.setClassroomResources(ClassroomClassroomResources);
            classrooms.add(classroom);
        }

        return classrooms;
    }
}
