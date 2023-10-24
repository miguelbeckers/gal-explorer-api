package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.Tempo;

@Repository
public interface TempoRepository extends JpaRepository<Tempo, Integer> {
}
