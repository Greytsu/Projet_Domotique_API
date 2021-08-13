package com.example.ProjetDomotiqueAPI.models.appareil;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import com.example.ProjetDomotiqueAPI.models.utilisateur.Utilisateur;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AppareilRepository {

    private MySqlConnection sqlCon;

    public AppareilRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public List<Appareil> findAllDevices(){

        String queryDevices =
            """
                select AP_ID, AP_Nom, PI_ID, AP_MAC
                from appareil""";
        List<Appareil> appareils = new ArrayList<>();

        try{
            var optResults = this.sqlCon.ExecQuery(queryDevices);
            if(optResults.isPresent()){
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int AP_ID = results.getInt("AP_ID");
                        String AP_NOM = results.getString("AP_NOM");
                        int PI_ID = results.getInt("PI_ID");
                        String AP_MAC = results.getString("AP_MAC");

                        appareils.add(new Appareil(AP_ID, AP_NOM, PI_ID, AP_MAC));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appareils;
    }

    public Optional<Appareil> findDeviceByID(int AP_ID){

        String queryDevice =
            """ 
                select AP_ID, AP_Nom, PI_ID, AP_MAC
                from appareil
                where AP_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryDevice);
            ps.setInt(1, AP_ID);

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()) {
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    results.next();
                    String AP_NOM = results.getString("AP_NOM");
                    int PI_ID = results.getInt("PI_ID");
                    String AP_MAC = results.getString("AP_MAC");

                    return Optional.of(new Appareil(AP_ID, AP_NOM, PI_ID, AP_MAC));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    public int updateDevice(Appareil device){

        String updateDevice =
                """
                    update Appareil
                    set AP_Nom = ?, PI_ID = ?
                    where AP_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(updateDevice);
            ps.setString(1, device.getAP_NOM());
            ps.setInt(2, device.getPI_ID());
            ps.setInt(3, device.getAP_ID());

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }

}
