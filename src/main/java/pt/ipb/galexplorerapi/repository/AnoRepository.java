package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
