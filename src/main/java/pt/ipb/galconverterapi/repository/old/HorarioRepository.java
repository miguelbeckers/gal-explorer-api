package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}
