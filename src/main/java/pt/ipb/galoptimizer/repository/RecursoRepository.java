package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
}
