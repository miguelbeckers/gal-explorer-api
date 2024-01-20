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
@Table(name = "recurso_sala", schema = "2022_2023_1_horarios")
public class RecursoSala {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_rec")
    private int idRec;
    @Column(name = "id_sala")
    private int idSala;
    @Column(name = "quantidade")
    private int quantidade;
}
