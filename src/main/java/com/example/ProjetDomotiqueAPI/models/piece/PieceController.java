package com.example.ProjetDomotiqueAPI.models.piece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/v1/piece")
public class PieceController {

    private final PieceService pieceService;

    @Autowired
    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    //GET---------------------------------------------------------------------------------------------------------------
    @GetMapping
    public List<Piece> getAllRooms(){
        return pieceService.findAllRooms();
    }

    @GetMapping(path = "{pieceID}")
    public List<Piece> getRoomById(){
        return pieceService.findRoomById();
    }

    @GetMapping(path = "sample")
    public List<Piece> getSample(){
        return List.of(
                new Piece(1, "Bureau Olivier", 1),
                new Piece(2, "Bureau Lucas", 1),
                new Piece(3, "Bureau Alain", 1)
        );
    }

    //POST--------------------------------------------------------------------------------------------------------------
}
