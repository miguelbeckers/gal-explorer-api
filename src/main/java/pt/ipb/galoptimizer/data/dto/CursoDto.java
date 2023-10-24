package pt.ipb.galoptimizer.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class CursoDto {
    private int id;
    private String nome;
    private List<AnoDto> anoDtos;
}
