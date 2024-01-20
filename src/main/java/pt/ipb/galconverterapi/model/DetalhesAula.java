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
@Table(name = "detalhes_aula", schema = "2022_2023_1_horarios")
public class DetalhesAula {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_curso")
    private int idCurso;
    @Column(name = "id_ano")
    private int idAno;
    @Column(name = "id_discip")
    private int idDiscip;
    @Column(name = "id_aula")
    private int idAula;
    @Column(name = "id_tipo_aula")
    private int idTipoAula;
    @Column(name = "turma")
    private String turma;
}
