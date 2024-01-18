package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Indisponibilidade;

import java.util.List;

@Repository
public interface IndisponibilidadeRepository extends JpaRepository<Indisponibilidade, Integer> {
    @Query(value = "SELECT * FROM indisponibilidade", nativeQuery = true)
    List<Object[]> findAllByQueryAsObjects();
}
