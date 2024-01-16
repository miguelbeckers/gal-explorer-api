package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.ClassroomConverter;
import pt.ipb.galconverterapi.model._new.Classroom;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassroomRepository {
    @Autowired
    private ClassroomConverter classroomConverter;

    private List<Classroom> classrooms = new ArrayList<>();

    public void load(){
        classrooms = classroomConverter.convert();
    }

    public List<Classroom> findAll(){
        return classrooms;
    }
}
