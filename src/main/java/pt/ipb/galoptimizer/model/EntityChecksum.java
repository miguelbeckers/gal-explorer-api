package pt.ipb.galoptimizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "entity_checksum", schema = "2022_2023_1_horarios")
//TODO: Esta entidade não pode ser persistida pois não possúi um id
public class EntityChecksum {
    @Column(name = "id_chkp")
    private int idChkp;
    @Column(name = "id_entity")
    private int idEntity;
    @Column(name = "checksum")
    private String checksum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityChecksum that = (EntityChecksum) o;
        return idChkp == that.idChkp && idEntity == that.idEntity && Objects.equals(checksum, that.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChkp, idEntity, checksum);
    }
}
