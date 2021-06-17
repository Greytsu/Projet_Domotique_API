package com.example.ProjetDomotiqueAPI.models.appareil;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Appareil {

    @Id

    private int AP_ID;
    private String AP_NOM;
    private int PI_ID;

    //CTOR--------------------------------------------------------------------------------------------------------------
    public Appareil(int AP_ID, String AP_NOM, int PI_ID) {
        this.AP_ID = AP_ID;
        this.AP_NOM = AP_NOM;
        this.PI_ID = PI_ID;
    }

    public Appareil() {
    }

    //Getter------------------------------------------------------------------------------------------------------------
    @JsonProperty("AP_ID")
    public int getAP_ID() {
        return AP_ID;
    }

    @JsonProperty("AP_NOM")
    public String getAP_NOM() {
        return AP_NOM;
    }

    @JsonProperty("PI_ID")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("getTest")
    public int getTest() {
        return 123;
    }
}
