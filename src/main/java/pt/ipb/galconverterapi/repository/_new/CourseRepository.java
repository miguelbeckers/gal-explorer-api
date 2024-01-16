package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.CourseConverter;
import pt.ipb.galconverterapi.model._new.Course;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    @Autowired
    private CourseConverter courseConverter;

    private List<Course> courses = new ArrayList<>();

    public void load() {
        courses = courseConverter.convert();
    }

    public List<Course> findAll() {
        return courses;
    }
}
