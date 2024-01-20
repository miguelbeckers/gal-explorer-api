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
@Table(name = "tempo", schema = "2022_2023_1_horarios")
public class Tempo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inicio")
    private Time inicio;
    @Column(name = "fim")
    private Time fim;
    @Column(name = "descricao")
    private String descricao;
}
