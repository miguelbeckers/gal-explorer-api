package pt.ipb.galoptimizer.schema1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//It cannot be an entity because it has no id
public class EntityChecksum {
    @Column(name = "id_chkp")
    private int idChkp;
    @Column(name = "id_entity")
    private int idEntity;
    @Column(name = "checksum")
    private String checksum;
}
