package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
}
