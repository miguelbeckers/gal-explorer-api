package pt.ipb.galconverterapi.model._new;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesAulaDto {
    private Long idAnoCurso;
    private Long idDisciplina;
    private Long idTipoAula;
    private Long idDocente;
    private String turma;
    private Long idAula;
    private Long idDetalheAula;
}
