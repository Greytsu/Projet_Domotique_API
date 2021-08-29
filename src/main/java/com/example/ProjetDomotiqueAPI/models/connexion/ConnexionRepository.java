package com.example.ProjetDomotiqueAPI.models.connexion;

import com.example.ProjetDomotiqueAPI.database.MySqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ConnexionRepository {

    private MySqlConnection sqlCon;

    public ConnexionRepository() {
        this.sqlCon = new MySqlConnection();
    }

    public int insertConnexion(Connexion connexion){

        String insertConnexion =
            """
                insert into Connexion
                (U_Login, C_Token, C_Creation)
                values(?, ?, CURRENT_TIMESTAMP())""";

        try {
            PreparedStatement ps = sqlCon.getCon().prepareStatement(insertConnexion);
            ps.setString(1, connexion.getU_Login());
            ps.setString(2, connexion.getC_Token());

            return this.sqlCon.ExecPreparedDataManip(ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;

    }

}
