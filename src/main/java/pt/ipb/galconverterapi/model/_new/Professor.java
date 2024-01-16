package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Professor {
    private Long id;
    private String name;
    private String abbreviation;
    private Department department;
    private List<Timeslot> unavailability = new ArrayList<>();

    private double eti;
    private String mail;
    private double credito;
    private Integer ipbCodEscola;
    private Integer ipbEmpNum;
}
