package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.RecursoDisciplina;

@Repository
public interface RecursoDisciplinaRepository extends JpaRepository<RecursoDisciplina, Integer> {
}
