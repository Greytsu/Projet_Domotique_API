package com.example.ProjetDomotiqueAPI.models.utilisateur;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurRepository(PasswordEncoder passwordEncoder) {
        this.sqlCon = new MySqlConnection();
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilisateur> findAll(){
        String queryUsers =
            """
                select U_ID, U_Login, U_Password, (select TU_Nom from TypeUtilisateur where TU_ID = Utilisateur.TU_ID) as TU_Nom 
                from Utilisateur""";
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try{
            var optResults = this.sqlCon.ExecQuery(queryUsers);
            if(optResults.isPresent()){
                ResultSet results = optResults.get();
                if(results.isBeforeFirst()){
                    while(results.next()){
                        int U_ID = results.getInt("U_ID");
                        String TU_Nom = results.getString("TU_Nom");
                        String U_Login = results.getString("U_Login");
                        String U_Password = results.getString("U_Password");

                        utilisateurs.add(new Utilisateur(U_ID, U_Login, U_Password, TU_Nom));
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
                select U_ID, U_Login, U_Password, (select TU_Nom from TypeUtilisateur where TU_ID =  Utilisateur.TU_ID) as TU_Nom
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
                    String TU_Nom = results.getString("TU_Nom");
                    String U_Login = results.getString("U_Login");
                    String U_Password = results.getString("U_Password");

                    return Optional.of(new Utilisateur(U_ID, U_Login, U_Password, TU_Nom));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public int insertUser(Utilisateur user){

        String insertUserPrep =
            """
                insert into Utilisateur
                (U_Login, U_Password, TU_ID)
                values (?, ?, (select TU_ID from TypeUtilisateur where TU_Nom = ?))""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(insertUserPrep);
            ps.setString(1, user.getU_Login());
            ps.setString(2, passwordEncoder.encode(user.getU_Password()));
            ps.setString(3, user.getTU_Nom());

            System.out.println(ps);

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }

    public int updateUser(Utilisateur user){

        String updateUserPrep =
            """
                update Utilisateur
                set U_Login = ?, TU_ID = (select TU_ID from TypeUtilisateur where TU_Nom = ?)
                where U_ID = ?""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(updateUserPrep);
            ps.setString(1, user.getU_Login());
            ps.setString(2, user.getTU_Nom());
            ps.setInt(3, user.getU_ID());

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }
}