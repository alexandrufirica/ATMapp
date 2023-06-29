package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DepositGUI extends RecentTransactionService implements ActionListener {
        JFrame frame;
        JPanel panel;
        JLabel amountLabel;
        JButton backButton;
        JButton depositButton;
        JTextField amountField;
        JLabel messageLabel;
        int amount;

        ResultSet resultSet;

        public DepositGUI(){
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


            JPanel panelLeft = new JPanel();
            panelLeft.setLayout(new GridLayout(4,1));

            JPanel panelRight = new JPanel();
            panelRight.setLayout(new GridLayout(4,1));

            JPanel panelUp = new JPanel();

            JPanel panelDown = new JPanel();
            panelDown.setLayout(new BorderLayout());

            messageLabel = new JLabel();

            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000,1000);
            frame.setResizable(false);
            frame.setTitle("ATMapp");

            panel = new JPanel();
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

            amountLabel = new JLabel();
            amountLabel.setText("Enter deposit amount : ");
            amountLabel.setHorizontalAlignment(JLabel.CENTER);
            amountLabel.setVerticalAlignment(JLabel.CENTER);
            amountLabel.setForeground(Color.YELLOW);
            amountLabel.setFont(new Font("Arial", Font.PLAIN, 30));

            amountField = new JTextField();
            amountField.setPreferredSize(new Dimension(200,40));
            amountField.setOpaque(false);
            amountField.setFont(new Font("Arial", Font.PLAIN,25));
            amountField.setForeground(Color.YELLOW);
            amountField.setCaretColor(Color.YELLOW);
            amountField.setVisible(true);

            backButton = new JButton();
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

            depositButton = new JButton();
            depositButton.setText("Deposit");
            depositButton.setFont(new Font("Arial", Font.PLAIN, 30));
            depositButton.setFocusable(false);
            depositButton.setIcon(rightArrow);
            depositButton.setHorizontalTextPosition(JButton.LEFT);
            depositButton.setVerticalTextPosition(JButton.CENTER);
            depositButton.setIconTextGap(10);
            depositButton.setForeground(Color.YELLOW);
            depositButton.setBackground(Color.BLUE);
            depositButton.setBorderPainted(false);
            depositButton.addActionListener(this);

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

            panelRight.add(depositButton);

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

        public void ballanceUpdate(String uname, int ballance){
            String SQL = "UPDATE atmusers "
                    + "SET ballance = ? "
                    + "WHERE username = ?";

            try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

                pstmt.setInt(1,ballance);
                pstmt.setString(2,uname);

                pstmt.executeUpdate();

            }catch ( SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                frame.dispose();
                new MainMenuGUI();

            }else if (e.getSource() == depositButton){
                try {
                    amount = Integer.parseInt(amountField.getText());
                    ballance = ballance + amount;
                    ballanceUpdate(uname,ballance);
                    recentTransaction(LocalDateTime.now().toString(),uname,"deposit");
                    messageLabel.setText("You deposit " + amount + currency );
                    messageLabel.setVisible(true);

                }catch (Exception ex){
                    messageLabel.setText("Something wrong! Try again!");
                    messageLabel.setVisible(true);
                }
            }


        }

}
