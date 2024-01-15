package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.DocenteExame;

@Repository
public interface DocenteExameRepository extends JpaRepository<DocenteExame, Integer> {
}
