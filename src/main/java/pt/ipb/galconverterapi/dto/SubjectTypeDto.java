package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class SubjectTypeDto {
    private Long id;
    private String name;
    private String abbreviation;

    private String cor;
    private int prioridade;
}
