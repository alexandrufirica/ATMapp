package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppService {

    public Connection con;
    public PreparedStatement pst;

    public static String uname;
    public static String pass;
    public static double ballance;
    public static String currency;
    public static int withdrawalLimit;

    public double  USDEUR;
    public double  USDGBP;
    public double  USDCAD;
    public double  USDRON;

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

    public void recentTransaction(double amount,String transatctionName)  {
        ConnectATMDB();

        String SQL = "INSERT INTO transactionlog"
                + "(date, username, transactiontype, amount, currency)"
                + "VALUES (?, ?, ?, ?, ?)";

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy hh:mm:ss");
        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setString(1, LocalDateTime.now().format(f).toString());
            pstmt.setString(2,uname);
            pstmt.setString(3,transatctionName);
            pstmt.setDouble(4,amount);
            pstmt.setString(5,currency);

            pstmt.executeUpdate();

        }catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void getTodayRates(){
        ResultSet resultSet;

        try {
            ConnectCurrencyRates();

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
            String todayDate = LocalDateTime.now().format(dateFormat).toString();

            pst = con.prepareStatement("select * from rates where date = ?");
            pst.setString(1,todayDate);

            resultSet = pst.executeQuery();

            while(resultSet.next()){
                USDEUR = resultSet.getDouble(2);
                USDGBP = resultSet.getDouble(3);
                USDCAD = resultSet.getDouble(4);
                USDRON = resultSet.getDouble(5);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public double updateRate(String currency, double fromAmount){
        double toAmount;
        double rate = 0;

        switch (currency){

            //USD rates
            case "USDUSD" : rate = 1; break;
            case "USDEUR" : rate = USDEUR; break;
            case "USDGBP" : rate = USDGBP; break;
            case "USDCAD" : rate = USDCAD; break;
            case "USDRON" : rate = USDRON; break;

            //EUR rates
            case "EUREUR" : rate = 1; break;
            case "EURUSD" : rate = 1/USDEUR; break;
            case "EURGBP" : rate = 1/USDEUR * USDGBP; break;
            case "EURCAD" : rate = 1/USDEUR * USDCAD; break;
            case "EURRON" : rate = 1/USDEUR * USDRON; break;

            //GBP rates
            case "GBPGBP" : rate = 1; break;
            case "GBPUSD" : rate = 1/USDGBP; break;
            case "GBPEUR" : rate = 1/USDGBP * USDEUR; break;
            case "GBPCAD" : rate = 1/USDGBP * USDCAD; break;
            case "GBPRON" : rate = 1/USDGBP * USDRON; break;

            //CAD rates
            case "CADCAD" : rate = 1; break;
            case "CADUSD" : rate = 1/USDCAD; break;
            case "CADEUR" : rate = 1/USDCAD * USDEUR; break;
            case "CADGBP" : rate = 1/USDCAD * USDGBP; break;
            case "CADRON" : rate = 1/USDCAD * USDRON; break;

            //RON rates
            case "RONRON" : rate = 1; break;
            case "RONUSD" : rate = 1/USDRON; break;
            case "RONEUR" : rate = 1/USDRON * USDEUR; break;
            case "RONGBP" : rate = 1/USDRON * USDGBP; break;
            case "RONCAD" : rate = 1/USDRON * USDCAD; break;

        }

        toAmount = fromAmount * rate;

        return toAmount;
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

    public static double getBallance() {
        return ballance;
    }

    public static void setBallance(double ballance) {
        AppService.ballance = ballance;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        AppService.currency = currency;
    }

    public static double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public static void setWithdrawalLimit(int withdrawalLimit) {
        AppService.withdrawalLimit = withdrawalLimit;
    }

}
