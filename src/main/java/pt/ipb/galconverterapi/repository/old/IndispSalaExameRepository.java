package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.IndispSalaExame;

@Repository
public interface IndispSalaExameRepository extends JpaRepository<IndispSalaExame, Integer> {
}
