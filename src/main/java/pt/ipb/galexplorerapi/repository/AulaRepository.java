package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
