package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
