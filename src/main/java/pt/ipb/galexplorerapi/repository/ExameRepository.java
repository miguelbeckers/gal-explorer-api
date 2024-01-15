package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
}
