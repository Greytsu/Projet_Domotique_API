package com.example.ProjetDomotiqueAPI.models.utilisateur;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UtilisateurRepository {

private MySqlConnection sqlCon;

    public UtilisateurRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public List<Utilisateur> findAll(){
        String queryUsers = "select * from Utilisateur";
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try{
            var optResults = this.sqlCon.ExecQuery(queryUsers);
            if(optResults.isPresent()){
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int U_ID = results.getInt("U_ID");
                        int TU_ID = results.getInt("TU_ID");
                        String U_Login = results.getString("U_Login");
                        String U_Password = results.getString("U_Password");

                        utilisateurs.add(new Utilisateur(U_ID, U_Login, U_Password, TU_ID));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return utilisateurs;
    }

    public Optional<Utilisateur> findByUsername(String username){

        String queryUser =
                """ 
                    select *
                    from Utilisateur
                    where U_Login = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(queryUser);
            ps.setString(1, username);

            var optResults = this.sqlCon.ExecPreparedQuery(ps);
            if(optResults.isPresent()) {
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    results.next();
                    int U_ID = results.getInt("U_ID");
                    int TU_ID = results.getInt("TU_ID");
                    String U_Login = results.getString("U_Login");
                    String U_Password = results.getString("U_Password");

                    return Optional.of(new Utilisateur(U_ID, U_Login, U_Password, TU_ID));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

}