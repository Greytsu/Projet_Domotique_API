package com.example.ProjetDomotiqueAPI.models.doneee;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
public class Donnee {

    @Id
    private int DO_ID;
    private float DO_Valeur;
    private int AP_ID;
    private int TD_ID;
    private int PI_ID;
    private Timestamp DO_Creation;


    //CTOR--------------------------------------------------------------------------------------------------------------
    public Donnee() {
    }

    public Donnee(int DO_ID, float DO_Valeur, int AP_ID, int TD_ID, int PI_ID, Timestamp DO_Creation) {
        this.DO_ID = DO_ID;
        this.DO_Valeur = DO_Valeur;
        this.AP_ID = AP_ID;
        this.TD_ID = TD_ID;
        this.PI_ID = PI_ID;
        this.DO_Creation = DO_Creation;
    }

    public Donnee(float DO_Valeur, int TD_ID, int PI_ID, Timestamp DO_Creation) {
        this.DO_Valeur = DO_Valeur;
        this.TD_ID = TD_ID;
        this.PI_ID = PI_ID;
        this.DO_Creation = DO_Creation;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public int getDO_ID() {
        return DO_ID;
    }

    @JsonProperty("value")
    public float getDO_Valeur() {
        return DO_Valeur;
    }

    @JsonProperty("device_id")
    public int getAP_ID() {
        return AP_ID;
    }

    @JsonProperty("value_type")
    public int getTD_ID() {
        return TD_ID;
    }

    @JsonProperty("room_id")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("timestamp")
    public Timestamp getDO_Creation() {
        return DO_Creation;
    }
}
