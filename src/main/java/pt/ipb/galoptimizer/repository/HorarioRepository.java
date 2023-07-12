package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}
