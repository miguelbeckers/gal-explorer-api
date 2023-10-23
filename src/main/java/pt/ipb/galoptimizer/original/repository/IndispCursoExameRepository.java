package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.IndispCursoExame;

@Repository
public interface IndispCursoExameRepository extends JpaRepository<IndispCursoExame, Integer> {
}
