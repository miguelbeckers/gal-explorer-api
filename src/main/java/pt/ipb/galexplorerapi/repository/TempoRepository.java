package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Tempo;

@Repository
public interface TempoRepository extends JpaRepository<Tempo, Integer> {
}
