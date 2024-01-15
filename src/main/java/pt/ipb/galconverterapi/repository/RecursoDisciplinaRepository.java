package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.RecursoDisciplina;

import java.util.List;

@Repository
public interface RecursoDisciplinaRepository extends JpaRepository<RecursoDisciplina, Integer> {
    List<RecursoDisciplina> findByIdDiscip(int idDiscip);
}
