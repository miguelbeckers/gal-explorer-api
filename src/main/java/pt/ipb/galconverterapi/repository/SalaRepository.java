package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
}
