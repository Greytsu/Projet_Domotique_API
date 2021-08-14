package com.example.ProjetDomotiqueAPI.security.authentification;

import com.example.ProjetDomotiqueAPI.models.utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthentificationRep {

    private String jwt;
    private Utilisateur user;

    public AuthentificationRep(String jwt, Utilisateur user) {
        this.jwt = jwt;
        this.user = user;
    }

    @JsonProperty("token")
    public String getJwt() {
        return jwt;
    }

    @JsonProperty("user")
    public Utilisateur getUser() {
        return user;
    }
}
