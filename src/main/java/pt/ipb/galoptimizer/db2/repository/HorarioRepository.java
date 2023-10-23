package pt.ipb.galoptimizer.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.db2.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}
