package pt.ipb.galoptimizer.schema1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.schema1.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
