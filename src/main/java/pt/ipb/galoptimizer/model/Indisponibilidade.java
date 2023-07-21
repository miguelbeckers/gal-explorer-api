package pt.ipb.galoptimizer.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//It cannot be an entity because it has no id
public class Indisponibilidade {
    @Column(name = "tipo")
    private Object tipo;
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Column(name = "id_ano")
    private Integer idAno;
    @Column(name = "id_dia")
    private int idDia;
    @Column(name = "inicio")
    private Time inicio;
    @Column(name = "fim")
    private Time fim;
}
