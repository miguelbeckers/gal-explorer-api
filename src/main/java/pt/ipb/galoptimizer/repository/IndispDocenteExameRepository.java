package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.IndispDocenteExame;

@Repository
public interface IndispDocenteExameRepository extends JpaRepository<IndispDocenteExame, Integer> {
}
