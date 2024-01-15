package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.SalaExame;

@Repository
public interface SalaExameRepository extends JpaRepository<SalaExame, Integer> {
}
