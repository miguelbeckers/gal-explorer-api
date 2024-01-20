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
@Table(name = "aluno_disciplina", schema = "2022_2023_1_horarios")
public class AlunoDisciplina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_aluno")
    private int idAluno;
    @Column(name = "id_discip")
    private int idDiscip;
    @Column(name = "id_curso")
    private int idCurso;
    @Column(name = "ano")
    private int ano;
}
