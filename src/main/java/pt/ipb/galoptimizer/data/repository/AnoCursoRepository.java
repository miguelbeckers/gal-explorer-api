package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.AnoCurso;

import java.util.List;

@Repository
public interface AnoCursoRepository extends JpaRepository<AnoCurso, Integer> {
    List<AnoCurso> findByIdCurso(int idCurso);
}
