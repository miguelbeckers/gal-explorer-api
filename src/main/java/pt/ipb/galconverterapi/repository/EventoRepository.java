package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
