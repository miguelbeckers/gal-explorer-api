package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.modelToDto.*;

@Controller
@CrossOrigin
@RequestMapping("/get")
public class GetterController {
    @Autowired
    private DepartmentDtoConverter departmentDtoConverter;

    @Autowired
    private PeriodDtoConverter periodDtoConverter;

    @Autowired
    private ProfessorDtoConverter professorDtoConverter;

    @Autowired
    private ResourceDtoConverter resourceDtoConverter;

    @Autowired
    private SubjectTypeDtoConverter subjectTypeDtoConverter;

    @Autowired
    private TimeslotDtoConverter timeslotDtoConverter;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello getter!");
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> getDepartments() {
        return ResponseEntity.ok().body(departmentDtoConverter.convert());
    }

    @GetMapping("/periods")
    public ResponseEntity<Object> getPeriods() {
        return ResponseEntity.ok().body(periodDtoConverter.convert());
    }

    @GetMapping("/professors")
    public ResponseEntity<Object> getProfessors() {
        return ResponseEntity.ok().body(professorDtoConverter.convert());
    }

    @GetMapping("/resources")
    public ResponseEntity<Object> getResources() {
        return ResponseEntity.ok().body(resourceDtoConverter.convert());
    }

    @GetMapping("/subjectTypes")
    public ResponseEntity<Object> getSubjectTypes() {
        return ResponseEntity.ok().body(subjectTypeDtoConverter.convert());
    }

    @GetMapping("/timeslots")
    public ResponseEntity<Object> getTimeslots() {
        return ResponseEntity.ok().body(timeslotDtoConverter.convert());
    }
}
