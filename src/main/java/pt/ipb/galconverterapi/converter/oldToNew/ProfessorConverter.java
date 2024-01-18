package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Professor;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Docente;
import pt.ipb.galconverterapi.repository.old.DocenteRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

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

    public List<Professor> convert() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();

        List<Professor> professors = new ArrayList<>();
        for (Docente docente : docentes) {
            Professor professor = new Professor();
            professor.setId((long) docente.getId());
            professor.setName(docente.getNome());
            professor.setAbbreviation(docente.getAbrev());
            professor.setDepartment((long) docente.getIdDepart());

            List<Object[]> indisponibilidadesProfessor = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("D") && idTipo == docente.getId()) {
                    indisponibilidadesProfessor.add(indisponibilidade);
                }
            }

            List<Timeslot> professorUnavailability = timeslotConverter.convert(indisponibilidadesProfessor);
            professor.setUnavailability(professorUnavailability.stream().map(Timeslot::getId).toList());

            professors.add(professor);
        }

        return professors;
    }
}
