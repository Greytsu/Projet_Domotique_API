package com.example.ProjetDomotiqueAPI.dto;

import com.example.ProjetDomotiqueAPI.models.doneee.Donnee;
import com.example.ProjetDomotiqueAPI.models.donneeReference.DonneeReference;
import com.example.ProjetDomotiqueAPI.models.piece.Piece;

import java.util.List;

public class PieceResponse {

    private int id;
    private String name;
    private int room_type;
    private List<Donnee> last_datas;
    private List<DonneeReference> references;

    public PieceResponse(Piece piece, List<Donnee> last_datas, List<DonneeReference> references) {
        this.id = piece.getPI_ID();
        this.name = piece.getPI_Nom();
        this.room_type = piece.getTP_ID();

        this.last_datas = last_datas;
        this.references = references;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoom_type() {
        return room_type;
    }

    public List<Donnee> getLast_datas() {
        return last_datas;
    }

    public List<DonneeReference> getReferences() {
        return references;
    }

    //SETTER------------------------------------------------------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoom_type(int room_type) {
        this.room_type = room_type;
    }

    public void setLast_datas(List<Donnee> last_datas) {
        this.last_datas = last_datas;
    }

    public void setReferences(List<DonneeReference> references) {
        this.references = references;
    }
}
