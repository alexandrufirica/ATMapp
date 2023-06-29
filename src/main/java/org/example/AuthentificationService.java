package org.example;

import java.sql.*;

public class AuthentificationService {

    public Connection con;
    public PreparedStatement pst;

    public static String uname;
    public static String pass;
    public static int ballance;
    public static String currency;
    public static int withdrawalLimit;

    public void Connect() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATMDB", "postgres", "admin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getUname() {
        return uname;
    }

    public static void setUname(String uname) {
        AuthentificationService.uname = uname;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        AuthentificationService.pass = pass;
    }

    public static int getBallance() {
        return ballance;
    }

    public static void setBallance(int ballance) {
        AuthentificationService.ballance = ballance;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        AuthentificationService.currency = currency;
    }

    public static int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public static void setWithdrawalLimit(int withdrawalLimit) {
        AuthentificationService.withdrawalLimit = withdrawalLimit;
    }

}
