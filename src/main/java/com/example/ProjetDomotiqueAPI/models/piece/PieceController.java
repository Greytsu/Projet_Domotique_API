package com.example.ProjetDomotiqueAPI.models.piece;

import com.example.ProjetDomotiqueAPI.dto.PieceResponse;
import com.example.ProjetDomotiqueAPI.models.doneee.DonneeService;
import com.example.ProjetDomotiqueAPI.models.donneeReference.DonneeReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "ProjetDomotique/api/v1/piece")
public class PieceController {


    private final PieceService pieceService;

    private final DonneeService donneeService;

    private final DonneeReferenceService donneeReferenceService;

    @Autowired
    public PieceController(PieceService pieceService, DonneeService donneeService, DonneeReferenceService donneeReferenceService) {
        this.pieceService = pieceService;
        this.donneeService = donneeService;
        this.donneeReferenceService = donneeReferenceService;
    }

    //GET---------------------------------------------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<?> getAllRooms(){

        List<Piece> pieces = pieceService.findAllRooms();
        List<PieceResponse> response = new ArrayList<>();

        for (Piece piece : pieces) {
            response.add(new PieceResponse(piece, donneeService.getLastDatas(piece.getPI_ID()), donneeReferenceService.findReferencesByRoomId(piece.getPI_ID())));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{PI_ID}")
    public ResponseEntity<?> getRoomById(@PathVariable("PI_ID") int PI_ID){

        var optPiece = pieceService.findRoomById(PI_ID);
        if (optPiece.isEmpty())
            return null;

        Piece piece = optPiece.get();
        return ResponseEntity.ok(new PieceResponse(piece, donneeService.getLastDatas(PI_ID), donneeReferenceService.findReferencesByRoomId(piece.getPI_ID())));

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
    public Piece postPiece(@RequestBody Piece piece){
        return pieceService.insertRoom(piece);
    }

    //PUT---------------------------------------------------------------------------------------------------------------
    @PutMapping
    public boolean putRoom(@RequestBody Piece piece){
        return pieceService.updateRoom(piece) > 0;
    }
}