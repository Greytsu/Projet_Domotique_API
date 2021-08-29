package com.example.ProjetDomotiqueAPI.models.connexion;

import java.sql.Timestamp;

public class Connexion {

    private int C_ID;
    private String U_Login;
    private String C_Token;
    private Timestamp C_Creation;

    public Connexion() {
    }

    public Connexion(String u_Login, String c_Token) {
        U_Login = u_Login;
        C_Token = c_Token;
    }

    public Connexion(int c_ID, String u_Login, String c_Token, Timestamp c_Creation) {
        C_ID = c_ID;
        U_Login = u_Login;
        C_Token = c_Token;
        C_Creation = c_Creation;
    }

    //GETTER------------------------------------------------------------------------------------------------------------
    public int getC_ID() {
        return C_ID;
    }

    public String getU_Login() {
        return U_Login;
    }

    public String getC_Token() {
        return C_Token;
    }

    public Timestamp getC_Creation() {
        return C_Creation;
    }

    //SETTER------------------------------------------------------------------------------------------------------------
    public void setC_ID(int c_ID) {
        C_ID = c_ID;
    }

    public void setU_Login(String u_Login) {
        U_Login = u_Login;
    }

    public void setC_Token(String c_Token) {
        C_Token = c_Token;
    }

    public void setC_Creation(Timestamp c_Creation) {
        C_Creation = c_Creation;
    }
}
