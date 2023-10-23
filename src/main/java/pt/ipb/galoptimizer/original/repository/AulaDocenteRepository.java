package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.AulaDocente;

@Repository
public interface AulaDocenteRepository extends JpaRepository<AulaDocente, Integer> {
}
