package org.example;

import java.sql.*;

public class AuthentificationService {

    public Connection con;
    public PreparedStatement pst;
    public static String getUname() {
        return uname;
    }


    public static String uname;
    public static String pass;
    public static int ballance;
    public static String currency;
    public static String withdrawalLimit;


    public void Connect(){

        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATMDB","postgres","admin");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
