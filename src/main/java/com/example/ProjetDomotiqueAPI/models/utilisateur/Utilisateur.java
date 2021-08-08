package com.example.ProjetDomotiqueAPI.models.utilisateur;

import com.example.ProjetDomotiqueAPI.models.autorisation.Autorisation;
import com.example.ProjetDomotiqueAPI.models.autorisation.AutorisationController;
import com.example.ProjetDomotiqueAPI.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import static com.example.ProjetDomotiqueAPI.security.ApplicationUserRole.*;

@Entity
@Table
public class Utilisateur {

    @Id
    private Integer U_ID;
    private String U_Login;
    private String U_Password;
    private String TU_Nom;
    private ApplicationUserRole role;

    //CTOR--------------------------------------------------------------------------------------------------------------
    public Utilisateur() {
    }

    public Utilisateur(String U_Login, String U_Password, String TU_Nom, List<Autorisation> autorisations) {
        this.U_Login = U_Login;
        this.U_Password = U_Password;
        this.TU_Nom = TU_Nom;
        setRole();
    }

    public Utilisateur(int u_ID, String u_Login, String u_Password, String TU_Nom) {
        U_ID = u_ID;
        U_Login = u_Login;
        U_Password = u_Password;
        this.TU_Nom = TU_Nom;
        setRole();
    }

    //Methods-----------------------------------------------------------------------------------------------------------
    public void setDefaultUserType(){
        setTU_Nom("USER");
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public int getU_ID() {
        return U_ID;
    }

    @JsonProperty("login")
    public String getU_Login() {
        return U_Login;
    }

    @JsonProperty("password")
    public String getU_Password() {
        return U_Password;
    }

    @JsonProperty("user_type")
    public String getTU_Nom() {
        return TU_Nom;
    }

    @JsonProperty("role")
    public ApplicationUserRole getRole() {
        return role;
    }

    @JsonProperty("authorizations")
    public List<Autorisation> getAutorisations() {
        return AutorisationController.getAutorisationsSample();
    }

    //SETTER------------------------------------------------------------------------------------------------------------

    @JsonProperty("id")
    public void setU_ID(Integer u_ID) {
        U_ID = u_ID;
    }

    @JsonProperty("login")
    public void setU_Login(String u_Login) {
        U_Login = u_Login;
    }

    @JsonProperty("password")
    public void setU_Password(String u_Password) {
        U_Password = u_Password;
    }

    @JsonProperty("user_type")
    public void setTU_Nom(String TU_Nom) {
        this.TU_Nom = TU_Nom;
        setRole();
    }

    private void setRole(){
        switch(this.TU_Nom){
            case "SUPER_ADMIN" :
                this.role = SUPER_ADMIN;
                break;
            case "ADMIN" :
                this.role = ADMIN;
                break;

            case "USER" :
                this.role = USER;
                break;

            default :
                this.role = CONSULTANT;
        }
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "U_ID=" + U_ID +
                ", U_Login='" + U_Login + '\'' +
                ", U_Password='" + U_Password + '\'' +
                ", TU_Nom='" + TU_Nom + '\'' +
                ", role=" + role +
                '}';
    }
}
