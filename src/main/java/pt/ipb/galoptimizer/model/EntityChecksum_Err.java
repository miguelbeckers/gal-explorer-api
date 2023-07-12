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
public class EntityChecksum_Err {
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
        EntityChecksum_Err that = (EntityChecksum_Err) o;
        return idChkp == that.idChkp && idEntity == that.idEntity && Objects.equals(checksum, that.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChkp, idEntity, checksum);
    }
}
