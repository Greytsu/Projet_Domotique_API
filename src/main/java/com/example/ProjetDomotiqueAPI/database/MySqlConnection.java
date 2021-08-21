package com.example.ProjetDomotiqueAPI.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Optional;

@Component
public class MySqlConnection {

    private static Connection con;
    private String url;
    private String user;
    private String password;

    public MySqlConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        con = null;

        connect();
    }

    public MySqlConnection() {
        this.url = "jdbc:mysql://localhost:3306/Projet_Domotique?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.user = "ProjetDomotique_API";
        this.password = "7DPh9pVa6PtVvjxLr4E7";

        connect();
    }

    public static Connection getCon() {
        return con;
    }

    private void connect(){
        if (con == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                        this.url,
                        this.user,
                        this.password);

            }catch(SQLException | ClassNotFoundException e){
                System.out.println(e);
            }
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    //Query
    public Optional<ResultSet> ExecQuery(String req){
        try{
            Statement stmt= con.createStatement();
            return Optional.of(stmt.executeQuery(req));
        }catch(Exception e){
            System.out.println(e);
            return Optional.empty();
        }
    }

    public Optional<ResultSet> ExecPreparedQuery(PreparedStatement preparedStmt){
        try{
            return Optional.of(preparedStmt.executeQuery());
        }catch(Exception e){
            System.out.println(e);
            return Optional.empty();
        }
    }

    //Data manip
    public int ExecDataManip(String req){
        try{
            Statement stmt= con.createStatement();
            return stmt.executeUpdate(req);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int ExecPreparedDataManip(PreparedStatement preparedStmt){
        try{
            return preparedStmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
