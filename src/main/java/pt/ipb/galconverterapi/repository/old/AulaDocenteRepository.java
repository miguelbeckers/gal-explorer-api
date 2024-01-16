package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.AulaDocente;

import java.util.List;

@Repository
public interface AulaDocenteRepository extends JpaRepository<AulaDocente, Integer> {
    List<AulaDocente> findByIdDocente(Integer docenteId);
    List<AulaDocente> findByIdAula(Integer aulaId);
}
