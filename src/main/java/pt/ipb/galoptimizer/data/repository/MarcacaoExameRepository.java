package pt.ipb.galoptimizer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.data.model.MarcacaoExame;

@Repository
public interface MarcacaoExameRepository extends JpaRepository<MarcacaoExame, Integer> {
}
