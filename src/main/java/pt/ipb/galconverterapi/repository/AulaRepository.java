package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
