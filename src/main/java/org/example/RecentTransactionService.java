package org.example;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.*;
import com.opencsv.CSVWriter;

public class RecentTransactionService extends AuthentificationService {

    public void recentTransaction(int amount,String transatctionName)  {
        Connect();

        String SQL = "INSERT INTO transactionlog"
                + "(date, username, transactiontype, amount, currency)"
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setString(1,LocalDateTime.now().toString());
            pstmt.setString(2,uname);
            pstmt.setString(3,transatctionName);
            pstmt.setInt(4,amount);
            pstmt.setString(5,currency);

            pstmt.executeUpdate();

        }catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
