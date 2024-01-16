package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.RecursoSala;

import java.util.List;

@Repository
public interface RecursoSalaRepository extends JpaRepository<RecursoSala, Integer> {
    List<RecursoSala> findByIdRec(int idRec);
}
