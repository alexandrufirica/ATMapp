package org.example;

public class Logs {

    String date;
    String username;
    String transactionType;
    int transcationAmount;
    String transactionCurrency;
    public String Logs(String date, String username, String transactionType, int transcationAmount, String transactionCurrency){

        this.date = date;
        this.username = username;
        this.transactionType = transactionType;
        this.transcationAmount = transcationAmount;
        this.transactionCurrency =transactionCurrency;

        return date + " " + username + " " + transactionType + " " + transcationAmount + " " + transactionCurrency ;

    }

}
