package com.example.ProjetDomotiqueAPI.models.piece;

import com.example.ProjetDomotiqueAPI.dto.PieceResponse;
import com.example.ProjetDomotiqueAPI.models.doneee.DonneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/piece")
public class PieceController {


    private final PieceService pieceService;

    private final DonneeService donneeService;

    @Autowired
    public PieceController(PieceService pieceService, DonneeService donneeService) {
        this.pieceService = pieceService;
        this.donneeService = donneeService;
    }

    //GET---------------------------------------------------------------------------------------------------------------
    @GetMapping
    public List<Piece> getAllRooms(){
        return pieceService.findAllRooms();
    }

    @GetMapping(path = "{PI_ID}")
    public ResponseEntity<?> getRoomById(@PathVariable("PI_ID") int PI_ID){

        var optPiece = pieceService.findRoomById(PI_ID);
        if (optPiece.isEmpty())
            return null;

        return ResponseEntity.ok(new PieceResponse(optPiece.get(), donneeService.getLastDatas(PI_ID)));

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
    public boolean postPiece(@RequestBody Piece piece){
        return pieceService.insertRoom(piece) > 0;
    }

    //PUT---------------------------------------------------------------------------------------------------------------
    @PutMapping
    public boolean putRoom(@RequestBody Piece piece){
        return pieceService.updateRoom(piece) > 0;
    }
}