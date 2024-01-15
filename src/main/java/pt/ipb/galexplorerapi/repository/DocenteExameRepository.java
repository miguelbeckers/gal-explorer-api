package pt.ipb.galexplorerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galexplorerapi.model.DocenteExame;

@Repository
public interface DocenteExameRepository extends JpaRepository<DocenteExame, Integer> {
}
