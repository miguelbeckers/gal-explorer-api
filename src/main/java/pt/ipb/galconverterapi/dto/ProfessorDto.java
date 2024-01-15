package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfessorDto {
    private Long id;
    private String name;
    private String abbreviation;
    private DepartmentDto departmentDto;
    private List<TimeslotDto> unavailability = new ArrayList<>();

    private double eti;
    private String mail;
    private double credito;
    private Integer ipbCodEscola;
    private Integer ipbEmpNum;
}
