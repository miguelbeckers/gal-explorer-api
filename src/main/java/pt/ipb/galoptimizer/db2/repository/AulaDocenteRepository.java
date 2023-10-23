package pt.ipb.galoptimizer.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.db2.model.AulaDocente;

@Repository
public interface AulaDocenteRepository extends JpaRepository<AulaDocente, Integer> {
}
