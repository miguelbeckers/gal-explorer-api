package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.IndispSalaExame;

@Repository
public interface IndispSalaExameRepository extends JpaRepository<IndispSalaExame, Integer> {
}