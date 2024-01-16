package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.RecursoDisciplina;

import java.util.List;

@Repository
public interface RecursoDisciplinaRepository extends JpaRepository<RecursoDisciplina, Integer> {
    List<RecursoDisciplina> findByIdDiscip(int idDiscip);
}
