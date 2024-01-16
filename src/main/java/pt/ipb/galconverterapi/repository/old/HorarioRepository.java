package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Horario;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    @Query(value = "SELECT ano_curso.id, detalhes_aula.id_discip, detalhes_aula.id_tipo_aula, \n" +
            "docente.abrev, turma, detalhes_aula.id_aula, detalhes_aula.id FROM detalhes_aula \n" +
            "INNER JOIN ano_curso ON detalhes_aula.id_ano = ano_curso.id_ano \n" +
            "AND detalhes_aula.id_curso = ano_curso.id_curso \n" +
            "INNER JOIN aula_docente ON detalhes_aula.id_aula = aula_docente.id_aula \n" +
            "INNER JOIN docente ON aula_docente.id_docente = docente.id", nativeQuery = true)
    public List<Object[]> findServicoDocentes();
}
