package com.example.ProjetDomotiqueAPI.models.piece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/piece")
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

    @GetMapping(path = "{PI_ID}")
    public Optional<Piece> getRoomById(@PathVariable("PI_ID") int PI_ID){
        return pieceService.findRoomById(PI_ID);
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
    @PostMapping
    public boolean postRoom(@RequestBody Piece piece){
        return pieceService.insertRoom(piece) > 0;
    }
}
