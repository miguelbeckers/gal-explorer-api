package pt.ipb.galoptimizer.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.original.model.Ano;

import java.util.List;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Integer> {
}
