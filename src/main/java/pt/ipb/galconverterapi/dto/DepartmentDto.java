package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private String abbreviation;

    private Integer ipbCodEscola;
    private Integer ipbEmpCcusto;
}