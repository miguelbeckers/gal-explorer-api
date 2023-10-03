package pt.ipb.galoptimizer.schema1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.schema1.model.DisciplinaExame;

@Repository
public interface DisciplinaExameRepository extends JpaRepository<DisciplinaExame, Integer> {
}
