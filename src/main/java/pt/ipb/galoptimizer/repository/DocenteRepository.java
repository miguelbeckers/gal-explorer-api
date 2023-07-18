package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
}
