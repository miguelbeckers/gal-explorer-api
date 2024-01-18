package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.TipoSala;

@Repository
public interface TipoSalaRepository extends JpaRepository<TipoSala, Integer> {
}
