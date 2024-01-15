package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.EpocaExame;

@Repository
public interface EpocaExameRepository extends JpaRepository<EpocaExame, Integer> {
}
