package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.RecursoDisciplina;

@Repository
public interface RecursoDisciplinaRepository extends JpaRepository<RecursoDisciplina, Integer> {
}
