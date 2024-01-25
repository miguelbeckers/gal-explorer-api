package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.DepartmentMapper;

@Controller
@CrossOrigin
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getDepartments() {
        return ResponseEntity.ok().body(departmentMapper.convert());
    }
}
