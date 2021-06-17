package com.example.ProjetDomotiqueAPI.models.utilisateur;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Utilisateur {

    @Id
    private Integer U_ID;
    private String U_Login;
    private String U_Password;
    private int TU_ID;

    public Utilisateur(int u_ID, String u_Login, String u_Password, int TU_ID) {
        U_ID = u_ID;
        U_Login = u_Login;
        U_Password = u_Password;
        this.TU_ID = TU_ID;
    }

    public Utilisateur() {
    }

    @JsonProperty("U_ID")
    public int getU_ID() {
        return U_ID;
    }

    @JsonProperty("U_Login")
    public String getU_Login() {
        return U_Login;
    }

    @JsonProperty("U_Password")
    public String getU_Password() {
        return U_Password;
    }

    @JsonProperty("TU_ID")
    public int getTU_ID() {
        return TU_ID;
    }
}
