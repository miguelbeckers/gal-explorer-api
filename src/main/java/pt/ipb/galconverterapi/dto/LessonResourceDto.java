package pt.ipb.galconverterapi.dto;

import lombok.Data;

@Data
public class LessonResourceDto {
    private Long id;
    private Long resource;
    private double quantity;
}
