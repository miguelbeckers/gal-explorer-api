package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
