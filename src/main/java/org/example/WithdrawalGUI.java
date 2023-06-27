package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WithdrawalGUI extends AuthentificationService implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel amountLabel;
    JButton backButton;
    JButton withdrawalButton;
    JTextField amountField;
    JLabel messageLabel;
    int remainAmount;
    int amount;
    ResultSet resultSet;

    public WithdrawalGUI(){
        try {
            Connect();

            pst = con.prepareStatement("select * from atmusers where username = ?");
            pst.setString(1,uname);


            resultSet = pst.executeQuery();
            if(resultSet.next()){
                ballance = resultSet.getInt(4);
                currency = resultSet.getString(5);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        frame = new JFrame();
        panel = new JPanel();
        amountField = new JTextField();
        amountLabel = new JLabel();
        backButton = new JButton();
        withdrawalButton = new JButton();
        messageLabel = new JLabel();

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(4,1));

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(4,1));

        JPanel panelUp = new JPanel();

        JPanel panelDown = new JPanel();
        panelDown.setLayout(new BorderLayout());


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setTitle("ATMapp");

        panel.setSize(1000,1000);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.BLUE);

        ImageIcon leftArrow = new ImageIcon("leftArrow.png");
        ImageIcon rightArrow = new ImageIcon("rightArrow.png");

        JLabel upperText = new JLabel();
        upperText.setText("Bank account: " + uname);
        upperText.setFont(new Font("Arial", Font.PLAIN, 50));
        upperText.setForeground(Color.yellow);
        upperText.setBackground(Color.BLUE);
        upperText.setOpaque(false);
        upperText.setVisible(true);
        

        amountField.setPreferredSize(new Dimension(200,30));
        amountField.setOpaque(false);
        amountField.setFont(new Font("Arial", Font.PLAIN,25));
        amountField.setForeground(Color.YELLOW);
        amountField.setCaretColor(Color.YELLOW);
        amountField.setVisible(true);

        amountLabel.setText("Enter withdrawal amount : ");
        amountLabel.setSize(100,100);
       // amountLabel.setHorizontalAlignment(JLabel.CENTER);
       // amountLabel.setVerticalAlignment(JLabel.CENTER);
        amountLabel.setForeground(Color.YELLOW);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 30));


        backButton.setText("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.setFocusable(false);
        backButton.setIcon(leftArrow);
        backButton.setHorizontalTextPosition(JButton.RIGHT);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setIconTextGap(10);
        backButton.setForeground(Color.YELLOW);
        backButton.setBackground(Color.BLUE);
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);

        withdrawalButton = new JButton();
        withdrawalButton.setText("Withdrawal");
        withdrawalButton.setFont(new Font("Arial", Font.PLAIN, 30));
        withdrawalButton.setFocusable(false);
        withdrawalButton.setIcon(rightArrow);
        withdrawalButton.setHorizontalTextPosition(JButton.LEFT);
        withdrawalButton.setVerticalTextPosition(JButton.CENTER);
        withdrawalButton.setIconTextGap(10);
        withdrawalButton.setForeground(Color.YELLOW);
        withdrawalButton.setBackground(Color.BLUE);
        withdrawalButton.setBorderPainted(false);
        withdrawalButton.addActionListener(this);

        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        messageLabel.setBackground(Color.BLUE);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVisible(false);
        panelDown.add(messageLabel);

        panelLeft.setPreferredSize(new Dimension(300,300));
        panelRight.setPreferredSize(new Dimension(300,300));
        panelUp.setPreferredSize(new Dimension(100,100));
        panelDown.setPreferredSize(new Dimension(100,100));

        panelLeft.setBackground(Color.BLUE);
        panelRight.setBackground(Color.BLUE);
        panelUp.setBackground(Color.BLUE);
        panelDown.setBackground(Color.BLUE);

        panelUp.add(upperText);

        panelLeft.add(backButton);

        panelRight.add(withdrawalButton);

        panel.add(amountLabel,BorderLayout.CENTER);
        panel.add(amountField,BorderLayout.CENTER);


        frame.add(panel,BorderLayout.CENTER);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelRight,BorderLayout.EAST);
        frame.add(panelUp, BorderLayout.NORTH);
        frame.add(panelDown, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

//This method modify ballance after withdrawal

    public int ballanceUpdate(String uname, int ballance){
        String SQL = "UPDATE atmusers "
                + "SET ballance = ? "
                + "WHERE username = ?";

        int affectedrows = 0;

        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setInt(1,ballance);
            pstmt.setString(2,uname);

            affectedrows = pstmt.executeUpdate();

        }catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }
        return  affectedrows;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            frame.dispose();
            new MainMenuGUI();

        }
        else if (e.getSource() == withdrawalButton){
            try {
                amount = Integer.parseInt(amountField.getText());
                if(ballance-amount < 0){
                    messageLabel.setText("Insufficient funds, check your ballance!");
                    messageLabel.setVisible(true);
                }else {
                    remainAmount = ballance - amount;
                    ballanceUpdate(uname, remainAmount);
                    messageLabel.setText("You withdrawal " + amount + currency);
                    messageLabel.setVisible(true);
                }

            }catch (Exception ex){
                messageLabel.setText("Something wrong! Try again!");
                messageLabel.setVisible(true);
            }
        }


    }
}
