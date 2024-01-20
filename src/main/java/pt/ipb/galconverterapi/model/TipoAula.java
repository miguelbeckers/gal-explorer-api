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
@Table(name = "tipo_aula", schema = "2022_2023_1_horarios")
public class TipoAula {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "abrev")
    private String abrev;
    @Column(name = "cor")
    private String cor;
    @Column(name = "prioridade")
    private int prioridade;
}
