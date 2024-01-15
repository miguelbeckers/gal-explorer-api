package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.DisciplinaExame;

@Repository
public interface DisciplinaExameRepository extends JpaRepository<DisciplinaExame, Integer> {
}
