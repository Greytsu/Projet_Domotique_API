package com.example.ProjetDomotiqueAPI.models.donneeReference;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DonneeReference {

    @Id
    private int DR_ID;
    private float DR_Valeur;
    private int PI_ID;
    private int TD_ID;

    //CTOR--------------------------------------------------------------------------------------------------------------
    public DonneeReference() {
    }

    public DonneeReference(float DR_Valeur, int PI_ID, int TD_ID) {
        this.DR_Valeur = DR_Valeur;
        this.PI_ID = PI_ID;
        this.TD_ID = TD_ID;
    }

    public DonneeReference(int DR_ID, float DR_Valeur, int PI_ID, int TD_ID) {
        this.DR_ID = DR_ID;
        this.DR_Valeur = DR_Valeur;
        this.PI_ID = PI_ID;
        this.TD_ID = TD_ID;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("DR_ID")
    public int getDR_ID() {
        return DR_ID;
    }

    @JsonProperty("DR_Valeur")
    public float getDR_Valeur() {
        return DR_Valeur;
    }

    @JsonProperty("PI_ID")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("TD_ID")
    public int getTD_ID() {
        return TD_ID;
    }
}
