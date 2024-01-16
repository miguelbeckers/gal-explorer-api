package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long id;
    private String name;
    private String abbreviation;
    private Department department;
    private List<Timeslot> unavailability = new ArrayList<>();

    private Integer ipbCodEscola;
    private Integer ipbCodCurso;
    private Integer ipbNPlano;
    private Date inicioAulas;
    private Date fimAulas;
}
