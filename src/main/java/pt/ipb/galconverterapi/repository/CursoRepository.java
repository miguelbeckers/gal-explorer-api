package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
