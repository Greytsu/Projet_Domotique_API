package com.example.ProjetDomotiqueAPI.models.utilisateur;

import com.example.ProjetDomotiqueAPI.models.appareil.Appareil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/v1/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUsers(){
        return utilisateurService.findAllUsers();
    }

    @GetMapping(path = "{username}")
    public List<Utilisateur> getUserByUsername(@PathVariable("username") String username){
        return utilisateurService.findUserByUsername(username);
    }

    @GetMapping(path = "sample")
    public List<Utilisateur> getUsersSample(){
        return List.of(
                new Utilisateur(1, "Olivier", "password", 1),
                new Utilisateur(2, "Alain", "password", 1),
                new Utilisateur(3, "Lucas", "password", 1)
        );
    }


}
