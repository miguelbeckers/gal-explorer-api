package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.AnoCurso;

@Repository
public interface AnoCursoRepository extends JpaRepository<AnoCurso, Integer> {
}
