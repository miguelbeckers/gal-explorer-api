package pt.ipb.galconverterapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horario", schema = "2022_2023_1_horarios")
public class Horario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_aula")
    private int idAula;
    @Column(name = "id_dia")
    private int idDia;
    @Column(name = "inicio")
    private Time inicio;
    @Column(name = "fim")
    private Time fim;
    @Column(name = "id_sala")
    private int idSala;
}
