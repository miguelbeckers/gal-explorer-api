package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
