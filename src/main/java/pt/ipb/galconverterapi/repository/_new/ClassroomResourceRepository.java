package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.ClassroomResourceConverter;
import pt.ipb.galconverterapi.model._new.ClassroomResource;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassroomResourceRepository {
    @Autowired
    private ClassroomResourceConverter classroomResourceConverter;

    List<ClassroomResource> classroomResources = new ArrayList<>();

    public void load(){
        classroomResources = classroomResourceConverter.convert();
    }

    public List<ClassroomResource> findAll(){
        return classroomResources;
    }
}
