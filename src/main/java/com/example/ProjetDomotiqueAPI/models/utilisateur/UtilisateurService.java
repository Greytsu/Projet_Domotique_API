package com.example.ProjetDomotiqueAPI.models.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository uRepository) {
        this.utilisateurRepository = uRepository;
    }

    public List<Utilisateur> findAllUsers(){
        System.out.println("findAllUsers");
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> findUserByUsername(String username){
        var optUtilisateur =  utilisateurRepository.findByUsername(username);
        return optUtilisateur.map(List::of).orElseGet(List::of);
    }

}
