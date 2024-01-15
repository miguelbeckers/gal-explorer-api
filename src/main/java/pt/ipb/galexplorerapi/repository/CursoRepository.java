package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
