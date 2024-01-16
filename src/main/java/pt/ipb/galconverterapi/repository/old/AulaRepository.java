package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
}
