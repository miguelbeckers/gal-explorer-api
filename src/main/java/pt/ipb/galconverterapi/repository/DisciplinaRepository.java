package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.Disciplina;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
