package pt.ipb.galoptimizer.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.galoptimizer.db1.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
