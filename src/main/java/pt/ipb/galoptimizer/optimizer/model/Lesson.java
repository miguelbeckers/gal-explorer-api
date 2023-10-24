package pt.ipb.galoptimizer.optimizer.model;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@Getter
@Setter
@NoArgsConstructor
@PlanningEntity
public class Lesson {
    private Long id;
    private String subject;
    private String teacher;
    private String studentGroup;
    private String color;
    private Integer groupSize;

    // Initialized/Changed during planning
    @ManyToOne
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    private Timeslot timeslot;

    @ManyToOne
    @PlanningVariable(valueRangeProviderRefs = "classroomRange")
    private Classroom classroom;

    @Override
    public String toString() {
        return subject + " [" + id + "]";
    }
}
