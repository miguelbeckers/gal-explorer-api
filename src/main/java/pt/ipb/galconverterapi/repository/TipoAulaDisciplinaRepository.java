package pt.ipb.galconverterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.TipoAulaDisciplina;

@Repository
public interface TipoAulaDisciplinaRepository extends JpaRepository<TipoAulaDisciplina, Integer> {
}
