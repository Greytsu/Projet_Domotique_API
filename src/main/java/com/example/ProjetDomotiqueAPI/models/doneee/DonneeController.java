package com.example.ProjetDomotiqueAPI.models.doneee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/donnee")
public class DonneeController {

    private final DonneeService donneeService;

    @Autowired
    public DonneeController(DonneeService donneeService) {
        this.donneeService = donneeService;
    }

    //GET---------------------------------------------------------------------------------------------------------------


    @GetMapping(path = "sample")
    public List<Donnee> getUsersSample(){
        return List.of(
                new Donnee(1,21.2f, 1, 1, 1),
                new Donnee(2,69.0f, 1, 2, 1),
                new Donnee(3,22.2f, 2, 1, 1),
                new Donnee(4,58.0f, 2, 2, 1),
                new Donnee(5,28.2f, 3, 1, 2),
                new Donnee(6,88.0f, 3, 2, 2),
                new Donnee(5,24.2f, 4, 1, 3),
                new Donnee(6,66.6f, 4, 2, 3)
        );
    }

    //POST--------------------------------------------------------------------------------------------------------------
}
