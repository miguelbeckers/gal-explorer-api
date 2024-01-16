package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class Subject {
    private Long id;
    private String name;
    private String code;

    private Integer idDepart;
    private Integer ipbCodEscola;
    private Integer ipbCodCurso;
    private Integer ipbNPlano;
    private Integer ipbNDisciplina;
    private Integer ipbNOpcao;
}
