package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
}
