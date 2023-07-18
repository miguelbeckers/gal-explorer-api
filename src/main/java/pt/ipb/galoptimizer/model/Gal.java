package pt.ipb.galoptimizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "gal", schema = "2022_2023_1_horarios")
//TODO: Não pode ser entidade porque não tem id
public class Gal {
    @Column(name = "inicio")
    private Date inicio;
    @Column(name = "fim")
    private Date fim;
    @Column(name = "semestre")
    private int semestre;
    @Column(name = "versao")
    private int versao;
    @Column(name = "data")
    private Timestamp data;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "ano1")
    private int ano1;
    @Column(name = "ano2")
    private int ano2;
    @Column(name = "cod_escola")
    private Short codEscola;
    @Column(name = "emails")
    private String emails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gal galErr = (Gal) o;
        return semestre == galErr.semestre && versao == galErr.versao && ano1 == galErr.ano1 && ano2 == galErr.ano2 && Objects.equals(inicio, galErr.inicio) && Objects.equals(fim, galErr.fim) && Objects.equals(data, galErr.data) && Objects.equals(descricao, galErr.descricao) && Objects.equals(codEscola, galErr.codEscola) && Objects.equals(emails, galErr.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fim, semestre, versao, data, descricao, ano1, ano2, codEscola, emails);
    }
}
