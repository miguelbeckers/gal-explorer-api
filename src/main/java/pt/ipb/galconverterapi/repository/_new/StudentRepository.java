package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.StudentConverter;
import pt.ipb.galconverterapi.model._new.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private StudentConverter studentConverter;

    private List<Student> students = new ArrayList<>();

    public void load() {
        students = studentConverter.convert();
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findById(Long id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst().orElseThrow(
                () -> new RuntimeException("Student not found")
        );
    }
}
