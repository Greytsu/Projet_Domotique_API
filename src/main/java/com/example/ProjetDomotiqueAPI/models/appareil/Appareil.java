package com.example.ProjetDomotiqueAPI.models.appareil;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Appareil {

    private int AP_ID;
    private String AP_NOM;
    private int PI_ID;
    private String AP_MAC;

    //CTOR--------------------------------------------------------------------------------------------------------------
    public Appareil(int AP_ID, String AP_NOM, int PI_ID, String AP_MAC) {
        this.AP_ID = AP_ID;
        this.AP_NOM = AP_NOM;
        this.PI_ID = PI_ID;
        this.AP_MAC = AP_MAC;
    }

    public Appareil(int AP_ID, String AP_NOM, int PI_ID) {
        this.AP_ID = AP_ID;
        this.AP_NOM = AP_NOM;
        this.PI_ID = PI_ID;
    }

    public Appareil() {
    }

    //Getter------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public int getAP_ID() {
        return AP_ID;
    }

    @JsonProperty("name")
    public String getAP_NOM() {
        return AP_NOM;
    }

    @JsonProperty("room_id")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("mac_address")
    public String getAP_MAC() {
        return AP_MAC;
    }

    //Setter------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public void setAP_ID(int AP_ID) {
        this.AP_ID = AP_ID;
    }

    @JsonProperty("name")
    public void setAP_NOM(String AP_NOM) {
        this.AP_NOM = AP_NOM;
    }

    @JsonProperty("room_id")
    public void setPI_ID(int PI_ID) {
        this.PI_ID = PI_ID;
    }

    @JsonProperty("mac_address")
    public void setAP_MAC(String AP_MAC) {
        this.AP_MAC = AP_MAC;
    }
}
