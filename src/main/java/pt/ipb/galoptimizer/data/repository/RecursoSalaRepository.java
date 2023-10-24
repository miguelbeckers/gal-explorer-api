package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.RecursoSala;

import java.util.List;

@Repository
public interface RecursoSalaRepository extends JpaRepository<RecursoSala, Integer> {
    List<RecursoSala> findByIdRec(int idRec);
}
