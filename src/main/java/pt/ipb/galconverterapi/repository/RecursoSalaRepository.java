package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.RecursoSala;

@Repository
public interface RecursoSalaRepository extends JpaRepository<RecursoSala, Integer> {
}
