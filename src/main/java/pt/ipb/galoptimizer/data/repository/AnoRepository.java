package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
