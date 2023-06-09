package com.example.ProjetDomotiqueAPI.models.donneeReference;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonneeReferenceRepository {

    private MySqlConnection sqlCon;

    public DonneeReferenceRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public List<DonneeReference>  findReferencesByRoomId(int PI_ID){

        List<DonneeReference> donneeReferences =  new ArrayList<>();
        String queryDonneeRef =
            """ 
                select DR_ID, DR_Valeur, PI_ID, TD_ID
                from DonneeReference
                where PI_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryDonneeRef);
            ps.setInt(1, PI_ID);

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()) {
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int DR_ID = results.getInt("DR_ID");
                        float DR_Valeur = results.getFloat("DR_Valeur");
                        int TD_ID = results.getInt("TD_ID");

                        donneeReferences.add(new DonneeReference(DR_ID, DR_Valeur, PI_ID, TD_ID));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return donneeReferences;
    }

    public int insertReference(DonneeReference donneeReference){

        String insertReference =
                """
                    insert into DonneeReference
                    (DR_Valeur, PI_ID, TD_ID)
                    values(?, ?, ?)""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(insertReference);
            ps.setFloat(1, donneeReference.getDR_Valeur());
            ps.setInt(2, donneeReference.getPI_ID());
            ps.setInt(3, donneeReference.getTD_ID());

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }

    public int deleteReferences(int TD_ID, int PI_ID){
        String deleteReferences =
                """
                    delete from DonneeReference
                    where TD_ID = ? and PI_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(deleteReferences);
            ps.setInt(1, TD_ID);
            ps.setInt(2, PI_ID);

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

}
