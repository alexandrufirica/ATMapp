package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.*;
import com.opencsv.CSVWriter;

public class RecentTransactionService extends AuthentificationService {



    public static void recentTransaction(String data, String username,String transatctionName) throws FileNotFoundException, IOException {

        List<String[]> csvData = createCsvDataSpecial(LocalDateTime.now().toString(),uname,transatctionName);

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (
                CSVWriter writer = new CSVWriter(new FileWriter("transactionlog.csv"))) {
            writer.writeAll(csvData);

        }

    }



        public static List<String[]> list = new ArrayList<>();
    private static List<String[]> createCsvDataSpecial(String data, String username, String transactionType) {

        String[] header = {"Date", "Username", "Transaction"};

        String[] record1 = {data, username, transactionType};


        if(list.contains(header)){
            list.add(record1);
        }else {
            list.add(header);
            list.add(record1);
        }

        return list;

    }



}
