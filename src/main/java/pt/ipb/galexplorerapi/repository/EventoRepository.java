package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
