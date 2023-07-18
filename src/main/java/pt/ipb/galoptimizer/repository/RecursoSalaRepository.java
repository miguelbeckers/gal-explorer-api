package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.RecursoSala;

@Repository
public interface RecursoSalaRepository extends JpaRepository<RecursoSala, Integer> {
}
