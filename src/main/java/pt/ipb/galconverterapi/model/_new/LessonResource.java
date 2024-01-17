package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class LessonResource {
    private Long id;
    private Resource resource;
    private double quantity;
}
