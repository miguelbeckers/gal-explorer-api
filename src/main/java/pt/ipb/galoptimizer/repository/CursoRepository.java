package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
