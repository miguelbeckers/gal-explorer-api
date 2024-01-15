package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class ClassroomResourceDto {
    private Long id;
    private Integer quantity;
    private ResourceDto resourceDto;
}
