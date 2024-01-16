package pt.ipb.galconverterapi.model._new;

import lombok.Data;

@Data
public class LessonResource {
    private Long id;
    private Resource resource;
    private double quantity;

    private int idRec;
    private int idCurso;
    private int idAno;
    private int idDiscip;
    private int idTipoAula;
    private String valorExacto;
}
