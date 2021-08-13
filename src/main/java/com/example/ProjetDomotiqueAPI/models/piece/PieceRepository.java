package com.example.ProjetDomotiqueAPI.models.piece;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PieceRepository {

    private MySqlConnection sqlCon;

    public PieceRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public List<Piece> findAllRooms(){
        String queryRooms =
                """
                    select PI_ID, PI_Nom, TP_ID
                    from Piece""";
        List<Piece> pieces = new ArrayList<>();

        try{
            var optResults = this.sqlCon.ExecQuery(queryRooms);
            if(optResults.isPresent()){
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int PI_ID = results.getInt("PI_ID");
                        String PI_Nom = results.getString("PI_Nom");
                        int TP_ID = results.getInt("TP_ID");

                        pieces.add(new Piece(PI_ID, PI_Nom, TP_ID));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pieces;
    }

    public Optional<Piece> findRoomByID(int PI_ID){
        String queryRoom =
            """ 
                select PI_Nom, TP_ID
                from Piece
                where PI_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryRoom);
            ps.setInt(1, PI_ID);

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()) {
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    results.next();
                    String PI_Nom = results.getString("PI_Nom");
                    int TP_ID = results.getInt("TP_ID");

                    return Optional.of(new Piece(PI_ID, PI_Nom, TP_ID));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    public int updateRoom(Piece room) {

        String updateRoom =
                """
                    update Piece
                    set PI_Nom = ?, TP_ID = ?
                    where PI_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(updateRoom);
            ps.setString(1, room.getPI_Nom());
            ps.setInt(2, room.getTP_ID());
            ps.setInt(3, room.getPI_ID());

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }
}
