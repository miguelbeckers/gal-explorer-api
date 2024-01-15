package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.IndispEscolaExame;

@Repository
public interface IndispEscolaExameRepository extends JpaRepository<IndispEscolaExame, Integer> {
}
