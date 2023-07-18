package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.CursoEpoca;

@Repository
public interface CursoEpocaRepository extends JpaRepository<CursoEpoca, Integer> {
}
