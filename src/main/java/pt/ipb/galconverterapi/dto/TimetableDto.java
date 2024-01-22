package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TimetableDto {
    private List<Long> timeslotIds;
    private List<Long> classroomIds;
    private List<Long> lessonUnitIds;
}
