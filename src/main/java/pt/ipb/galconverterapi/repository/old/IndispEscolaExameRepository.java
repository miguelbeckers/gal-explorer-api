package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.IndispEscolaExame;

@Repository
public interface IndispEscolaExameRepository extends JpaRepository<IndispEscolaExame, Integer> {
}
