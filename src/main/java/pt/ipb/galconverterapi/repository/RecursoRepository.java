package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Recurso;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
}
