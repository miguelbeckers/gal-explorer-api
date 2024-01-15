package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.EpocaExame;

@Repository
public interface EpocaExameRepository extends JpaRepository<EpocaExame, Integer> {
}
