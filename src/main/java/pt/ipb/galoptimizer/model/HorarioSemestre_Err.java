package pt.ipb.galoptimizer.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "horario_semestre", schema = "2022_2023_1_horarios")
public class HorarioSemestre_Err {
    @Column(name = "versao")
    private int versao;
    @Column(name = "data")
    private Date data;
    @Column(name = "inicio")
    private String inicio;
    @Column(name = "cursos")
    private String cursos;
    @Column(name = "disciplinas")
    private String disciplinas;
    @Column(name = "sala")
    private String sala;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioSemestre_Err that = (HorarioSemestre_Err) o;
        return versao == that.versao && Objects.equals(data, that.data) && Objects.equals(inicio, that.inicio) && Objects.equals(cursos, that.cursos) && Objects.equals(disciplinas, that.disciplinas) && Objects.equals(sala, that.sala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versao, data, inicio, cursos, disciplinas, sala);
    }
}
