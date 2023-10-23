package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.IndispEscolaExame;

@Repository
public interface IndispEscolaExameRepository extends JpaRepository<IndispEscolaExame, Integer> {
}
