package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.SalaExame;

@Repository
public interface SalaExameRepository extends JpaRepository<SalaExame, Integer> {
}
