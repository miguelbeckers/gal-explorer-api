package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.AnoCurso;

import java.util.List;

@Repository
public interface AnoCursoRepository extends JpaRepository<AnoCurso, Integer> {
    List<AnoCurso> findByIdCurso(int idCurso);
}
