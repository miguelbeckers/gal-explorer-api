package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class Department {
    private Long id;
    private String name;
    private String abbreviation;

    private Integer ipbCodEscola;
    private Integer ipbEmpCcusto;
}
