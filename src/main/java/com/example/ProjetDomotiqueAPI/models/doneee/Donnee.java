package com.example.ProjetDomotiqueAPI.models.doneee;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Donnee {

    @Id
    private int DO_ID;
    private float DO_Valeur;
    private int AP_ID;
    private int TD_ID;
    private int PI_ID;


    //CTOR--------------------------------------------------------------------------------------------------------------
    public Donnee() {
    }

    public Donnee(float DO_Valeur, int AP_ID, int TD_ID, int PI_ID) {
        this.DO_Valeur = DO_Valeur;
        this.AP_ID = AP_ID;
        this.TD_ID = TD_ID;
        this.PI_ID = PI_ID;
    }

    public Donnee(int DO_ID, float DO_Valeur, int AP_ID, int TD_ID, int PI_ID) {
        this.DO_ID = DO_ID;
        this.DO_Valeur = DO_Valeur;
        this.AP_ID = AP_ID;
        this.TD_ID = TD_ID;
        this.PI_ID = PI_ID;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("DO_ID")
    public int getDO_ID() {
        return DO_ID;
    }

    @JsonProperty("DO_Valeur")
    public float getDO_Valeur() {
        return DO_Valeur;
    }

    @JsonProperty("AP_ID")
    public int getAP_ID() {
        return AP_ID;
    }

    @JsonProperty("TD_ID")
    public int getTD_ID() {
        return TD_ID;
    }

    @JsonProperty("PI_ID")
    public int getPI_ID() {
        return PI_ID;
    }
}
