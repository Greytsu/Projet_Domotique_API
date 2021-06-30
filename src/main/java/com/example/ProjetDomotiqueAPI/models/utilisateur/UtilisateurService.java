package com.example.ProjetDomotiqueAPI.models.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository uRepository) {
        this.utilisateurRepository = uRepository;
    }

    public List<Utilisateur> findAllUsers(){
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findUserByUsername(String username){
        return utilisateurRepository.findByUsername(username);
    }

}
