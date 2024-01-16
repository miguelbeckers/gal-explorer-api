package pt.ipb.galconverterapi.repository.old;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.model.old.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}
