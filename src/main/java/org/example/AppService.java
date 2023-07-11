package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppService {

    public Connection con;
    public PreparedStatement pst;

    public static String uname;
    public static String pass;
    public static int ballance;
    public static String currency;
    public static int withdrawalLimit;

    public void ConnectATMDB() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ATMDB", "postgres", "admin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ConnectCurrencyRates() {

        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CurrencyRates", "postgres", "admin");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void recentTransaction(int amount,String transatctionName)  {
        ConnectATMDB();

        String SQL = "INSERT INTO transactionlog"
                + "(date, username, transactiontype, amount, currency)"
                + "VALUES (?, ?, ?, ?, ?)";

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy hh:mm:ss");
        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setString(1, LocalDateTime.now().format(f).toString());
            pstmt.setString(2,uname);
            pstmt.setString(3,transatctionName);
            pstmt.setInt(4,amount);
            pstmt.setString(5,currency);

            pstmt.executeUpdate();

        }catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static String getUname() {
        return uname;
    }

    public static void setUname(String uname) {
        AppService.uname = uname;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        AppService.pass = pass;
    }

    public static int getBallance() {
        return ballance;
    }

    public static void setBallance(int ballance) {
        AppService.ballance = ballance;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        AppService.currency = currency;
    }

    public static int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public static void setWithdrawalLimit(int withdrawalLimit) {
        AppService.withdrawalLimit = withdrawalLimit;
    }

}
