package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.SubjectMapper;

@Controller
@CrossOrigin
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectController(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getSubjects() {
        return ResponseEntity.ok().body(subjectMapper.map());
    }
}
