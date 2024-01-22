package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.AulaDocente;

@Repository
public interface AulaDocenteRepository extends JpaRepository<AulaDocente, Integer> {
}
