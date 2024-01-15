package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.RecursoSala;

import java.util.List;

@Repository
public interface RecursoSalaRepository extends JpaRepository<RecursoSala, Integer> {
    List<RecursoSala> findByIdRec(int idRec);
}
