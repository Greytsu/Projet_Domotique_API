package com.example.ProjetDomotiqueAPI.models.piece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PieceService {

    private PieceRepository pieceRepository;

    @Autowired
    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public List<Piece> findAllRooms(){
        return pieceRepository.findAllRooms();
    }

    public Optional<Piece> findRoomById(int PI_ID){
        return pieceRepository.findRoomByID(PI_ID);
    }

    public int updateRoom(Piece room){
        return pieceRepository.updateRoom(room);
    }

    public int insertRoom(Piece room){
        return pieceRepository.insertRoom(room);
    }

}
