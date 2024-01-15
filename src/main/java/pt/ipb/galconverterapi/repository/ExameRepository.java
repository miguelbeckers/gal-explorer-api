package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
}
