package com.example.ProjetDomotiqueAPI.models.appareil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "ProjetDomotique/v1/appareil")
public class AppareilController {

    private final AppareilService appareilService;

    @Autowired
    public AppareilController(AppareilService appareilService) {
        this.appareilService = appareilService;
    }

    @GetMapping
    public List<Appareil> getStudents(){
        return appareilService.findAllDevices();
    }

    @GetMapping(path = "{AP_ID}")
    public Optional<Appareil> getStudentsByID(@PathVariable("AP_ID") int AP_ID){
        return appareilService.findDeviceByID(AP_ID);
    }

    @GetMapping(path = "sample")
    public List<Appareil> getStudentsSample(){
        return List.of(
                new Appareil(1, "ESP1", 1),
                new Appareil(2, "ESP1", 1),
                new Appareil(3, "ESP2", 2),
                new Appareil(4, "ESP3", 3)
        );
    }
}
