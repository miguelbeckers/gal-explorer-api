package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.SubjectCourseConverter;
import pt.ipb.galconverterapi.model._new.SubjectCourse;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectCourseRepository {
    @Autowired
    private SubjectCourseConverter subjectCourseConverter;

    private List<SubjectCourse> subjectCourses = new ArrayList<>();

    public void load(){
        subjectCourses = subjectCourseConverter.convert();
    }

    public List<SubjectCourse> findAll(){
        return subjectCourses;
    }
}
