package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Disciplina;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    List<Disciplina> findByIdDepart(int idDepart);

}
