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
@Table(name = "docente", schema = "2022_2023_1_horarios")
public class Docente {
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
    @Column(name = "eti")
    private double eti;
    @Column(name = "mail")
    private String mail;
    @Column(name = "credito")
    private double credito;
    @Column(name = "ipb_cod_escola")
    private Integer ipbCodEscola;
    @Column(name = "ipb_emp_num")
    private Integer ipbEmpNum;
}
