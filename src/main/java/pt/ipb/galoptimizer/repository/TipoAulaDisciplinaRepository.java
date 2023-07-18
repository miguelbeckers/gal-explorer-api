package pt.ipb.galoptimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.model.TipoAulaDisciplina;

@Repository
public interface TipoAulaDisciplinaRepository extends JpaRepository<TipoAulaDisciplina, Integer> {
}
