package com.example.ProjetDomotiqueAPI.models.donneeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/v1/donneeReference")
public class DonneeReferenceController {

    private final DonneeReferenceService donneeReferenceService;

    @Autowired
    public DonneeReferenceController(DonneeReferenceService donneeReferenceService) {
        this.donneeReferenceService = donneeReferenceService;
    }

    //GET---------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "sample")
    public List<DonneeReference> getUsersSample(){
        return List.of(
                new DonneeReference(1, 23.5f, 1, 1),
                new DonneeReference(2, 75, 1, 2),
                new DonneeReference(3, 24.5f, 2, 1),
                new DonneeReference(4, 76, 2, 2),
                new DonneeReference(5, 25.5f, 3, 1),
                new DonneeReference(6, 77, 3, 2)
        );
    }

    //POST--------------------------------------------------------------------------------------------------------------

}
