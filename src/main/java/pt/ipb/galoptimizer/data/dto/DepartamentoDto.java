package pt.ipb.galoptimizer.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartamentoDto {
    private int id;
    private String nome;
    private String abrev;
    private List<DisciplinaDto> disciplinaDtos;
}
