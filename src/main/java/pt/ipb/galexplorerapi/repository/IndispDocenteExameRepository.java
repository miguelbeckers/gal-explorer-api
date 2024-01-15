package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.IndispDocenteExame;

@Repository
public interface IndispDocenteExameRepository extends JpaRepository<IndispDocenteExame, Integer> {
}
