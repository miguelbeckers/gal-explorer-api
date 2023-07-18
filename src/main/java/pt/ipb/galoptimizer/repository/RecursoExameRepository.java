package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.RecursoExame;

@Repository
public interface RecursoExameRepository extends JpaRepository<RecursoExame, Integer> {
}
