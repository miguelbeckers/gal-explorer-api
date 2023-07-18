package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
}
