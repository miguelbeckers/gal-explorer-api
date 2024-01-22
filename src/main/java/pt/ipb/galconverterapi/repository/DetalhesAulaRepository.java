package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.DetalhesAula;

@Repository
public interface DetalhesAulaRepository extends JpaRepository<DetalhesAula, Integer> {
}
