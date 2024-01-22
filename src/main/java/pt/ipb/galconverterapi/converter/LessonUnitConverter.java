package pt.ipb.galconverterapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipb.galconverterapi.dto.LessonDto;
import pt.ipb.galconverterapi.dto.LessonUnitDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonUnitConverter {
    @Autowired
    private LessonConverter lessonConverter;

    public List<LessonUnitDto> convert() {
        List<LessonDto> lessonDtos = lessonConverter.convert();

        List<LessonUnitDto> lessonUnitDtos = new ArrayList<>();
        long id = 1L;

        for(LessonDto lessonDto: lessonDtos){
            for(int i = 0; i < Math.ceil(lessonDto.getHoursPerWeek()); i++){
                LessonUnitDto lessonUnitDto = new LessonUnitDto();
                lessonUnitDto.setId(id++);
                lessonUnitDto.setLessonId(lessonDto.getId());
            }
        }

        return lessonUnitDtos;
    }
}
