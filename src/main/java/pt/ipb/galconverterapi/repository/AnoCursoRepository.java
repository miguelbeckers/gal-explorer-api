package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.AnoCurso;

@Repository
public interface AnoCursoRepository extends JpaRepository<AnoCurso, Integer> {
}
