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
@Table(name = "departamento", schema = "2022_2023_1_horarios")
public class Departamento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "abrev")
    private String abrev;
    @Column(name = "ipb_cod_escola")
    private Integer ipbCodEscola;
    @Column(name = "ipb_emp_ccusto")
    private Integer ipbEmpCcusto;
}
