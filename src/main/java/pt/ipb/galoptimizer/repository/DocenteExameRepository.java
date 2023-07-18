package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.DocenteExame;

@Repository
public interface DocenteExameRepository extends JpaRepository<DocenteExame, Integer> {
}
