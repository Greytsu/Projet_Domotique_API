package com.example.ProjetDomotiqueAPI.models.autorisation;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Autorisation {

    @Id
    private int AU_ID;
    private int PI_ID;
    private String PI_Nom;
    private int TP_ID;

    //CTOR--------------------------------------------------------------------------------------------------------------

    public Autorisation() {
    }

    public Autorisation(int PI_ID, String PI_Nom, int TP_ID) {
        this.PI_ID = PI_ID;
        this.PI_Nom = PI_Nom;
        this.TP_ID = TP_ID;
    }

    public Autorisation(int AU_ID, int PI_ID, String PI_Nom, int TP_ID) {
        this.AU_ID = AU_ID;
        this.PI_ID = PI_ID;
        this.PI_Nom = PI_Nom;
        this.TP_ID = TP_ID;
    }

    //GETTER------------------------------------------------------------------------------------------------------------

    @JsonProperty("AU_ID")
    public int getAU_ID() {
        return AU_ID;
    }

    @JsonProperty("PI_ID")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("PI_Nom")
    public String getPI_Nom() {
        return PI_Nom;
    }

    @JsonProperty("TP_ID")
    public int getTP_ID() {
        return TP_ID;
    }
}
