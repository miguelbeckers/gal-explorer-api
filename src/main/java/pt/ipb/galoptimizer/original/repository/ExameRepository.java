package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
}
