package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.IndispSalaExame;

@Repository
public interface IndispSalaExameRepository extends JpaRepository<IndispSalaExame, Integer> {
}
