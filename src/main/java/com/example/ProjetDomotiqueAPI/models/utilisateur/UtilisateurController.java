package com.example.ProjetDomotiqueAPI.models.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/admin/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    //GET---------------------------------------------------------------------------------------------------------------
    @GetMapping
    public List<Utilisateur> getAllUsers(){
        return utilisateurService.findAllUsers();
    }

    @GetMapping(path = "{username}")
    public Utilisateur getUserByUsername(@PathVariable("username") String username){
        var optUtilisateur = utilisateurService.findUserByUsername(username);
        return optUtilisateur.isPresent()? optUtilisateur.get() : null;
    }

    @GetMapping(path = "sample")
    public List<Utilisateur> getUsersSample(){
        return List.of(
                new Utilisateur(1, "Olivier", "password", "Super Admin"),
                new Utilisateur(2, "Alain", "password", "Admin"),
                new Utilisateur(3, "Lucas", "password", "User")
        );
    }

    //POST--------------------------------------------------------------------------------------------------------------

    @PostMapping
    public boolean insertUser(@RequestBody Utilisateur user){
        return utilisateurService.createUser(user) > 0;
    }
}
