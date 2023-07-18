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
//@Entity
//@Table(name = "indisponibilidade", schema = "2022_2023_1_horarios")
//TODO: Esta entidade não pode ser persistida pois não possúi um id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indisponibilidade that = (Indisponibilidade) o;
        return idDia == that.idDia && Objects.equals(tipo, that.tipo) && Objects.equals(idTipo, that.idTipo) && Objects.equals(idAno, that.idAno) && Objects.equals(inicio, that.inicio) && Objects.equals(fim, that.fim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, idTipo, idAno, idDia, inicio, fim);
    }
}
