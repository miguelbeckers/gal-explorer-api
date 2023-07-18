package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.AlunoDisciplina;

@Repository
public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Integer> {
}
