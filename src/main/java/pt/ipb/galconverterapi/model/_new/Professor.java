package pt.ipb.galconverterapi.model._new;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Professor {
    private Long id;
    private String name;
    private String abbreviation;
    private Long department;
    private List<Long> unavailability = new ArrayList<>();
}
