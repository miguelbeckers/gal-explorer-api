package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.DisciplinaExame;

@Repository
public interface DisciplinaExameRepository extends JpaRepository<DisciplinaExame, Integer> {
}
