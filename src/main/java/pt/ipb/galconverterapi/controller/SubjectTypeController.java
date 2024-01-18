package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.converter.SubjectTypeConverter;

@Controller
@CrossOrigin
@RequestMapping("/subject-types")
public class SubjectTypeController {
    @Autowired
    private SubjectTypeConverter subjectTypeConverter;

    @GetMapping
    public ResponseEntity<Object> getSubjectTypes() {
        return ResponseEntity.ok().body(subjectTypeConverter.convert());
    }
}
