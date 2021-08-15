package com.example.ProjetDomotiqueAPI.models.doneee;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DonneeRepository {

    private MySqlConnection sqlCon;

    public DonneeRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public List<Donnee> getDonneesAfter(String after, List<String> deviceIds, List<String> roomIds){

        String queryDonnees;
        List<Donnee> donnees = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append("""
                    select DO_ID, DO_Valeur, AP_ID, TD_ID, PI_ID, DO_Creation
                    from Donnee
                    where DO_ID > ?""");
        if(deviceIds.size() > 0)
            sb.append(" and AP_ID in (?)");
        if(roomIds.size() > 0)
            sb.append(" and PI_ID in (?)");
        sb.append(" LIMIT 250");

        queryDonnees = sb.toString();

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryDonnees);
            ps.setInt(1, Integer.parseInt(after));

            if(deviceIds.size() > 0){
                String result = deviceIds.stream().collect(Collectors.joining(","));
                ps.setString(2, result);
            }

            if(roomIds.size() > 0){
                String result = roomIds.stream().collect(Collectors.joining(","));
                if(deviceIds.size() > 0){
                    ps.setString(3,result);
                }else{
                    ps.setString(2,result);
                }

            }

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()){
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int DO_ID = results.getInt("DO_ID");
                        float DO_Valeur = results.getFloat("DO_Valeur");
                        int AP_ID = results.getInt("AP_ID");
                        int TD_ID = results.getInt("TD_ID");
                        int PI_ID = results.getInt("PI_ID");
                        Timestamp DO_Creation = results.getTimestamp("DO_Creation");

                        donnees.add(new Donnee(DO_ID, DO_Valeur, AP_ID, TD_ID, PI_ID, DO_Creation));
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return donnees;
    }

    public List<Donnee> getLastDatas(int PI_ID){

        List<Donnee> donnees = new ArrayList<>();
        String queryLastDatas =
            """
                                        
                select round(avg(DO_Valeur),2) as DO_Valeur, TD_ID, DO_Creation, PI_ID
                from Donnee
                group by TD_ID
                having DO_Creation > SUBTIME(CURRENT_TIMESTAMP(), "120") and PI_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryLastDatas);
            ps.setInt(1, PI_ID);

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()) {
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        float DO_Valeur = results.getFloat("DO_Valeur");
                        int TD_ID = results.getInt("TD_ID");
                        Timestamp DO_Creation = results.getTimestamp("DO_Creation");

                        donnees.add(new Donnee(DO_Valeur, TD_ID, PI_ID, DO_Creation));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return donnees;

    }


}
