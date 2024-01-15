package pt.ipb.galexplorerapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private DepartmentDto departmentDto;
    private List<TimeslotDto> unavailability = new ArrayList<>();
}
