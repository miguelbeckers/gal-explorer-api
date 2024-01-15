package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.IndispCursoExame;

@Repository
public interface IndispCursoExameRepository extends JpaRepository<IndispCursoExame, Integer> {
}
