package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
