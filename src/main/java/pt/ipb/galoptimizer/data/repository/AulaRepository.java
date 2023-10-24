package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
