package pt.ipb.galoptimizer.optimizer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Classroom {
    public Long id;
    private String name;
    private Integer capacity;

    @Override
    public String toString() {
        return name;
    }
}
