package pt.ipb.galconverterapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disciplina", schema = "2022_2023_1_horarios")
public class Disciplina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_depart")
    private Integer idDepart;
    @Column(name = "nome")
    private String nome;
    @Column(name = "abrev")
    private String abrev;
    @Column(name = "ipb_cod_escola")
    private Integer ipbCodEscola;
    @Column(name = "ipb_cod_curso")
    private Integer ipbCodCurso;
    @Column(name = "ipb_n_plano")
    private Integer ipbNPlano;
    @Column(name = "ipb_n_disciplina")
    private Integer ipbNDisciplina;
    @Column(name = "ipb_n_opcao")
    private Integer ipbNOpcao;
}
