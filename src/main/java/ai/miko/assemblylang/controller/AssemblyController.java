package ai.miko.assemblylang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.miko.assemblylang.service.AssemblyService;

@RestController
@RequestMapping("/assembly")
public class AssemblyController {
    @Autowired
    AssemblyService service;
    @PostMapping("/process")
    public String index(@RequestBody String code) {
        return service.process(code);
    }
    
}
