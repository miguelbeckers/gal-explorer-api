package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
}
