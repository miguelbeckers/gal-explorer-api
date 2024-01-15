package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.TipoAula;

@Repository
public interface TipoAulaRepository extends JpaRepository<TipoAula, Integer> {
}
