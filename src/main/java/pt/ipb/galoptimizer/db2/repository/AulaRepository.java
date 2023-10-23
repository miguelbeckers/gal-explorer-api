package pt.ipb.galoptimizer.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.db2.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
