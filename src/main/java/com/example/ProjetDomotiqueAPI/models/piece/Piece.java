package com.example.ProjetDomotiqueAPI.models.piece;

import com.example.ProjetDomotiqueAPI.models.doneee.DonneeService;
import com.example.ProjetDomotiqueAPI.models.donneeReference.DonneeReference;
import com.example.ProjetDomotiqueAPI.models.donneeReference.DonneeReferenceRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

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

    //GETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public int getPI_ID() {
        return PI_ID;
    }

    @JsonProperty("name")
    public String getPI_Nom() {
        return PI_Nom;
    }

    @JsonProperty("room_type")
    public int getTP_ID() {
        return TP_ID;
    }

    @JsonProperty("references")
    public List<DonneeReference> getReferences() {

        DonneeReferenceRepository donneeReferenceRepository = new DonneeReferenceRepository();

        return donneeReferenceRepository.findReferencesByRoomId(this.PI_ID);
    }

    //SETTER------------------------------------------------------------------------------------------------------------
    @JsonProperty("id")
    public void setPI_ID(int PI_ID) {
        this.PI_ID = PI_ID;
    }

    @JsonProperty("name")
    public void setPI_Nom(String PI_Nom) {
        this.PI_Nom = PI_Nom;
    }

    @JsonProperty("room_type")
    public void setTP_ID(int TP_ID) {
        this.TP_ID = TP_ID;
    }
}
