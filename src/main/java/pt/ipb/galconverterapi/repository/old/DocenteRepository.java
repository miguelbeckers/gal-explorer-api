package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Docente;

import java.util.List;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    List<Docente> findByIdDepart(Integer departamentoId);
}
