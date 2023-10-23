package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.DetalhesAula;

import java.util.List;

@Repository
public interface DetalhesAulaRepository extends JpaRepository<DetalhesAula, Integer> {
    List<DetalhesAula> findByIdAnoAndIdCurso(int idAno, int idCurso);

    List<DetalhesAula> findByIdAno(int idAno);

    List<DetalhesAula> findByIdCurso(int idCurso);
}
