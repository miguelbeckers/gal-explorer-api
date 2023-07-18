package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.DetalhesAula;

@Repository
public interface DetalhesAulaRepository extends JpaRepository<DetalhesAula, Integer> {
}
