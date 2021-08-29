package com.example.ProjetDomotiqueAPI.models.connexion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionService {

    private ConnexionRepository connexionRepository;

    @Autowired
    public ConnexionService(ConnexionRepository connexionRepository) {
        this.connexionRepository = connexionRepository;
    }

    public int insertConnexion(Connexion connexion){
        return connexionRepository.insertConnexion(connexion);
    }
}
