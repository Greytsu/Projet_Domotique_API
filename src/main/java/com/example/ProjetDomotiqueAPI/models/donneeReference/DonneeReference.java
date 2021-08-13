package com.example.ProjetDomotiqueAPI.models.donneeReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @JsonProperty("valid")
    public boolean isValid(){
        if (this.DR_Valeur < 0)
            return false;
        if (this.PI_ID <= 0)
            return false;
        if (this.TD_ID <= 0)
            return false;
        return true;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public int getDR_ID() {
        return DR_ID;
    }

    @JsonProperty("value")
    public float getDR_Valeur() {
        return DR_Valeur;
    }

    @JsonProperty("room_id")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("data_type_id")
    public int getTD_ID() {
        return TD_ID;
    }

    //SETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public void setDR_ID(int DR_ID) {
        this.DR_ID = DR_ID;
    }

    @JsonProperty("value")
    public void setDR_Valeur(float DR_Valeur) {
        this.DR_Valeur = DR_Valeur;
    }

    @JsonProperty("room_id")
    public void setPI_ID(int PI_ID) {
        this.PI_ID = PI_ID;
    }

    @JsonProperty("data_type_id")
    public void setTD_ID(int TD_ID) {
        this.TD_ID = TD_ID;
    }
}
