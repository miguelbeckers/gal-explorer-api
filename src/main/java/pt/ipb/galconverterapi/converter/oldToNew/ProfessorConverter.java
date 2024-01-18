package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model._new.Professor;
import pt.ipb.galconverterapi.model._new.Timeslot;
import pt.ipb.galconverterapi.model.old.Docente;
import pt.ipb.galconverterapi.repository._new.DepartmentRepository;
import pt.ipb.galconverterapi.repository.old.DocenteRepository;
import pt.ipb.galconverterapi.repository.old.IndisponibilidadeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorConverter {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @Autowired
    private IndisponibilidadeRepository indisponibilidadeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Professor> convert() {
        List<Docente> docentes = docenteRepository.findAll();
        List<Department> departments = departmentRepository.findAll();
        List<Object[]> indisponibilidades = indisponibilidadeRepository.findAllByQueryAsObjects();

        List<Professor> professors = new ArrayList<>();
        for (Docente docente : docentes) {
            Professor professor = new Professor();
            professor.setId((long) docente.getId());
            professor.setName(docente.getNome());
            professor.setAbbreviation(docente.getAbrev());

            Department department = departments.stream()
                    .filter(d -> d.getId().equals((long) docente.getIdDepart()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Department [" + docente.getIdDepart() + "] not found"));

            professor.setDepartment(department.getId());

            List<Object[]> indisponibilidadesProfessor = new ArrayList<>();

            for (Object[] indisponibilidade : indisponibilidades) {
                String tipo = (String) indisponibilidade[0];
                int idTipo = (int) indisponibilidade[1];

                if (tipo.equals("D") && idTipo == docente.getId()) {
                    indisponibilidadesProfessor.add(indisponibilidade);
                }
            }

            List<Timeslot> professorUnavailability = timeslotConverter.convert(indisponibilidadesProfessor);

            professor.setUnavailability(professorUnavailability.stream()
                    .map(Timeslot::getId)
                    .toList());

            professors.add(professor);
        }

        return professors;
    }
}
