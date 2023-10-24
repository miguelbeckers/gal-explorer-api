package pt.ipb.galoptimizer.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class DisciplinaDto {
    private int id;
    private String nome;
    private List<TipoAulaDto> tipoAulaDtos;
}
