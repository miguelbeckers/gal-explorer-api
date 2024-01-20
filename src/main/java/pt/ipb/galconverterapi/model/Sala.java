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
@Table(name = "sala", schema = "2022_2023_1_horarios")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_tipo")
    private int idTipo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "abrev")
    private String abrev;
}
