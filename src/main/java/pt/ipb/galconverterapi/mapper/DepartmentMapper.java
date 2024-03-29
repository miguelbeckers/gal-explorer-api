package pt.ipb.galconverterapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.DepartmentDto;
import pt.ipb.galconverterapi.model.Departamento;
import pt.ipb.galconverterapi.repository.DepartamentoRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {
    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartmentMapper(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<DepartmentDto> map() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Departamento departamento : departamentos) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId((long) departamento.getId());
            departmentDto.setName(departamento.getNome());
            departmentDto.setAbbreviation(departamento.getAbrev());
            departmentDtos.add(departmentDto);
        }

        return departmentDtos;
    }
}
