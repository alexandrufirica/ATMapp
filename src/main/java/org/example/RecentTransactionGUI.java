package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecentTransactionGUI extends AppService implements ActionListener {
    JFrame frame;
    JPanel panelCenter;
    JTextArea transactionLogsTextArea;
    JButton backButton;

    String date;
    String username;
    String transactionType;
    double transcationAmount;
    String transactionCurrency;

    String log;
    List <String> logsList = new ArrayList<>();
    public RecentTransactionGUI(){

        ResultSet resultSet;

        try {
            ConnectATMDB();

            pst = con.prepareStatement("select * from transactionlog where username = ?");
            pst.setString(1,uname);

            resultSet = pst.executeQuery();




            while(resultSet.next()){

                log =  GetLogs(resultSet.getString(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getDouble(4),
                                        resultSet.getString(5));

                logsList.add(log);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setTitle("ATMapp");

        panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.BLUE);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(4,1));

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(4,1));

        JPanel panelUp = new JPanel();

        JPanel panelDown = new JPanel();
        panelDown.setLayout(new BorderLayout());

        ImageIcon leftArrow = new ImageIcon("leftArrow.png");
        ImageIcon rightArrow = new ImageIcon("rightArrow.png");

        JLabel upperText = new JLabel();
        upperText.setText("Bank account: " + uname);
        upperText.setFont(new Font("Arial", Font.PLAIN, 50));
        upperText.setForeground(Color.yellow);
        upperText.setBackground(Color.BLUE);
        upperText.setOpaque(false);
        upperText.setVisible(true);

        transactionLogsTextArea = new JTextArea();
        transactionLogsTextArea.setText(String.valueOf(logsList).replace("[","").replace("]","").replace(",", ""));
        transactionLogsTextArea.setForeground(Color.YELLOW);
        transactionLogsTextArea.setBackground(Color.BLUE);
        transactionLogsTextArea.setEditable(false);
        transactionLogsTextArea.setFont(new Font("Arial", Font.PLAIN, 30));

        JScrollPane scrollPane = new JScrollPane(transactionLogsTextArea);
        scrollPane.setBackground(Color.BLUE);
        scrollPane.setForeground(Color.BLUE);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.setFocusable(false);
        backButton.setIcon(leftArrow);
        backButton.setHorizontalTextPosition(JButton.RIGHT);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setForeground(Color.YELLOW);
        backButton.setBackground(Color.BLUE);
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);

        panelLeft.setPreferredSize(new Dimension(180,300));
        panelRight.setPreferredSize(new Dimension(10,300));
        panelUp.setPreferredSize(new Dimension(100,100));
        panelDown.setPreferredSize(new Dimension(100,100));

        panelLeft.setBackground(Color.BLUE);
        panelRight.setBackground(Color.BLUE);
        panelUp.setBackground(Color.BLUE);
        panelDown.setBackground(Color.BLUE);

        panelUp.add(upperText);

        panelLeft.add(backButton);

        panelCenter.add(scrollPane,BorderLayout.CENTER);

        frame.add(panelCenter,BorderLayout.CENTER);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelRight,BorderLayout.EAST);
        frame.add(panelUp, BorderLayout.NORTH);
        frame.add(panelDown, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public String GetLogs(String date, String username, String transactionType, double transcationAmount, String transactionCurrency){

        this.date = date;
        this.username = username;
        this.transactionType = transactionType;
        this.transcationAmount = transcationAmount;
        this.transactionCurrency =transactionCurrency;

        String newline = System.lineSeparator();

        return  date + " " + username + " " + transactionType + " " + transcationAmount + " " + transactionCurrency + newline ;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenuGUI();
        }
    }
}
