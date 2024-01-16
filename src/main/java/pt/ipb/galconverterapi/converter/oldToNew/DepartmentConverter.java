package pt.ipb.galconverterapi.converter.oldToNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.model._new.Department;
import pt.ipb.galconverterapi.model.old.Departamento;
import pt.ipb.galconverterapi.repository.old.DepartamentoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentConverter {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Department> convert() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        List<Department> departments = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            Department department = new Department();
            department.setId((long) departamento.getId());
            department.setName(departamento.getNome());
            department.setAbbreviation(departamento.getAbrev());

            department.setIpbCodEscola(departamento.getIpbCodEscola());
            department.setIpbEmpCcusto(departamento.getIpbEmpCcusto());
            departments.add(department);
        }

        return departments;
    }
}
