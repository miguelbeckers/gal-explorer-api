package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.oldToNew.*;

@Controller
@CrossOrigin
@RequestMapping("/get")
public class GetterController {
    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private PeriodConverter periodConverter;

    @Autowired
    private ProfessorConverter professorConverter;

    @Autowired
    private ResourceConverter resourceConverter;

    @Autowired
    private SubjectTypeConverter subjectTypeConverter;

    @Autowired
    private TimeslotConverter timeslotConverter;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("hello getter!");
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> getDepartments() {
        return ResponseEntity.ok().body(departmentConverter.convert());
    }

    @GetMapping("/periods")
    public ResponseEntity<Object> getPeriods() {
        return ResponseEntity.ok().body(periodConverter.convert());
    }

    @GetMapping("/professors")
    public ResponseEntity<Object> getProfessors() {
        return ResponseEntity.ok().body(professorConverter.convert());
    }

    @GetMapping("/resources")
    public ResponseEntity<Object> getResources() {
        return ResponseEntity.ok().body(resourceConverter.convert());
    }

    @GetMapping("/subjectTypes")
    public ResponseEntity<Object> getSubjectTypes() {
        return ResponseEntity.ok().body(subjectTypeConverter.convert());
    }

    @GetMapping("/timeslots")
    public ResponseEntity<Object> getTimeslots() {
        return ResponseEntity.ok().body(timeslotConverter.convert());
    }
}
