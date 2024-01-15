package pt.ipb.galconverterapi.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassroomDto {
    private Long id;
    private String name;
    private List<TimeslotDto> unavailability = new ArrayList<>();
    private List<ClassroomResourceDto> classroomResourceDtos = new ArrayList<>();

    private String abbreviation;
    private int idTipo;
}
