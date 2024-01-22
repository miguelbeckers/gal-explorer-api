package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TimetableDto {
    private List<Long> timeslots;
    private List<Long> classrooms;
    private List<Long> lessonUnits;
}
