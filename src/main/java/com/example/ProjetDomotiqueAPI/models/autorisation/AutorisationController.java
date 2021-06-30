package com.example.ProjetDomotiqueAPI.models.autorisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/autorisations")
public class AutorisationController {

    @Autowired
    private final AutorisationService autorisationService;

    public AutorisationController(AutorisationService autorisationService) {
        this.autorisationService = autorisationService;
    }

    @GetMapping(path = "sample")
    public static List<Autorisation> getAutorisationsSample(){
        return List.of(
                new Autorisation(1, 1, "Bureau Olivier", 1),
                new Autorisation(2, 2, "Bureau Lucas", 1),
                new Autorisation(3, 3, "Bureau Alain", 1)
        );
    }
}
