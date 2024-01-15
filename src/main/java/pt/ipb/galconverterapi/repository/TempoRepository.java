package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Tempo;

@Repository
public interface TempoRepository extends JpaRepository<Tempo, Integer> {
}
