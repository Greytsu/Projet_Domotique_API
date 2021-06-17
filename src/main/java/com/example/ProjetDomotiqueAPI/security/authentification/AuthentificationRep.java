package com.example.ProjetDomotiqueAPI.security.authentification;

public class AuthentificationRep {

    private final String jwt;

    public AuthentificationRep(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
