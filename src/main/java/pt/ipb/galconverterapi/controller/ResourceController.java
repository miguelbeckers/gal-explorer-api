package pt.ipb.galconverterapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ipb.galconverterapi.mapper.ResourceMapper;

@Controller
@CrossOrigin
@RequestMapping("/resources")
public class ResourceController {
    private final ResourceMapper resourceMapper;

    @Autowired
    public ResourceController(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getResources() {
        return ResponseEntity.ok().body(resourceMapper.convert());
    }
}
