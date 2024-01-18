package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String abbreviation;
    private Long department;
    private List<Long> unavailability = new ArrayList<>();
}
