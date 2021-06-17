package com.example.ProjetDomotiqueAPI.models.piece;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Piece {

    @Id
    private int PI_ID;
    private String PI_Nom;
    private int TP_ID;

    //CTOR--------------------------------------------------------------------------------------------------------------
    public Piece() {
    }

    public Piece(int PI_ID, String PI_Nom, int TP_ID) {
        this.PI_ID = PI_ID;
        this.PI_Nom = PI_Nom;
        this.TP_ID = TP_ID;
    }

    public Piece(String PI_Nom, int TP_ID) {
        this.PI_Nom = PI_Nom;
        this.TP_ID = TP_ID;
    }

    //Getter------------------------------------------------------------------------------------------------------------

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
