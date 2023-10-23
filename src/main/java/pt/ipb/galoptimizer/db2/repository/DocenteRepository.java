package pt.ipb.galoptimizer.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.db2.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
}
