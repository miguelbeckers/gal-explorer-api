package pt.ipb.galconverterapi.dto;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String abbreviation;
    private DepartmentDto departmentDto;
    private List<TimeslotDto> unavailability = new ArrayList<>();

    private Integer ipbCodEscola;
    private Integer ipbCodCurso;
    private Integer ipbNPlano;
    private Date inicioAulas;
    private Date fimAulas;
}
