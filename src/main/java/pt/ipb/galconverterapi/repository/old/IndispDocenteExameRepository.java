package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.IndispDocenteExame;

@Repository
public interface IndispDocenteExameRepository extends JpaRepository<IndispDocenteExame, Integer> {
}
