package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
}
