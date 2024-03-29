package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Indisponibilidade;

import java.util.List;

@Repository
public interface IndisponibilidadeRepository extends JpaRepository<Indisponibilidade, Integer> {
    @Query(value = "select tipo, id_tipo, id_dia, inicio, fim from indisponibilidade", nativeQuery = true)
    List<Object[]> findAllByQueryAsObjects();
}
