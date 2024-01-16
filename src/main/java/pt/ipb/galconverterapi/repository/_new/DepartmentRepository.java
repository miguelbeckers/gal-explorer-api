package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.DepartmentConverter;
import pt.ipb.galconverterapi.model._new.Department;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {
    @Autowired
    private DepartmentConverter departmentConverter;

    private List<Department> departments = new ArrayList<>();

    public void load() {
        departments = departmentConverter.convert();
    }

    public List<Department> findAll() {
        return departments;
    }
}
