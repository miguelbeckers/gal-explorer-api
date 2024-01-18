package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Classroom;
import pt.ipb.galconverterapi.model._new.ClassroomResource;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.RecursoSala;
import pt.ipb.galconverterapi.model.old.Sala;
import pt.ipb.galconverterapi.repository._new.ClassroomResourceRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;
import pt.ipb.galconverterapi.repository.old.RecursoSalaRepository;
import pt.ipb.galconverterapi.repository.old.SalaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomConverter {
    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private RecursoSalaRepository recursoSalaRepository;

    @Autowired
    private ClassroomResourceRepository classroomResourceRepository;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    public List<Classroom> convert() {
        List<Sala> salas = salaRepository.findAll();
        List<RecursoSala> recursoSalas = recursoSalaRepository.findAll();

        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();
        List<ClassroomResource> classroomResources = classroomResourceRepository.findAll();

        List<Classroom> classrooms = new ArrayList<>();
        for (Sala sala : salas) {
            Classroom classroom = new Classroom();
            classroom.setId((long) sala.getId());
            classroom.setName(sala.getNome());
            classroom.setAbbreviation(sala.getAbrev());

            List<Object[]> indisponibilidadesSala = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("S") && idTipo == sala.getId()) {
                    indisponibilidadesSala.add(indisponibilidade);
                }
            }

            List<Timeslot> professorUnavailability = timeslotConverter.convert(indisponibilidadesSala);

            classroom.setUnavailability(professorUnavailability.stream()
                    .map(Timeslot::getId)
                    .toList());

            List<RecursoSala> recursoSalasSala = recursoSalas.stream()
                    .filter(recursoSala -> recursoSala.getIdSala() == sala.getId())
                    .toList();

            List<ClassroomResource> ClassroomClassroomResources = new ArrayList<>();
            for (RecursoSala recursoSala : recursoSalasSala) {
                ClassroomResource classroomResource = classroomResources.stream()
                        .filter(r -> r.getId().equals((long) recursoSala.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException("ClassroomResource [" + recursoSala.getId() + "] not found")
                        );

                ClassroomClassroomResources.add(classroomResource);
            }

            classroom.setClassroomResources(ClassroomClassroomResources.stream()
                    .map(ClassroomResource::getId)
                    .toList());

            classrooms.add(classroom);
        }

        return classrooms;
    }
}
