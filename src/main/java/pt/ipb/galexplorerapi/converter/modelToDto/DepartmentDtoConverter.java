package pt.ipb.galexplorerapi.converter.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galexplorerapi.dto.DepartmentDto;
import pt.ipb.galexplorerapi.model.Departamento;
import pt.ipb.galexplorerapi.repository.DepartamentoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDtoConverter {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public void convert() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId((long) departamento.getId());
            departmentDto.setName(departamento.getNome());
            departmentDto.setAbbreviation(departamento.getAbrev());

            departmentDto.setIpbCodEscola(departamento.getIpbCodEscola());
            departmentDto.setIpbEmpCcusto(departamento.getIpbEmpCcusto());
            departmentDtos.add(departmentDto);
        }
    }
}
