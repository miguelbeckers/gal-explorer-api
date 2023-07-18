package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
