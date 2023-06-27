package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferGUI extends AuthentificationService implements ActionListener {
    JFrame frame;
    JPanel panelCenter;
    JLabel amountLabel;
    JButton backButton;
    JButton transferButton;
    JTextField amountField;
    JLabel messageLabel;
    JLabel transferUserLabel;
    JTextField transferUserField;

    int amount;

    ResultSet resultSet;
    String targetUname;
    int targetBallance;
    String targetCurrency;
    public TransferGUI(){

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

        amountLabel = new JLabel();
        amountLabel.setText("Enter transfer amount : ");
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
        
        transferUserLabel = new JLabel();
        transferUserLabel.setText("Enter transfer target account: ");
        transferUserLabel.setHorizontalAlignment(JLabel.CENTER);
        transferUserLabel.setVerticalAlignment(JLabel.CENTER);
        transferUserLabel.setForeground(Color.YELLOW);
        transferUserLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        
        transferUserField = new JTextField();
        transferUserField.setPreferredSize(new Dimension(200,40));
        transferUserField.setOpaque(false);
        transferUserField.setFont(new Font("Arial", Font.PLAIN,25));
        transferUserField.setForeground(Color.YELLOW);
        transferUserField.setCaretColor(Color.YELLOW);
        transferUserField.setVisible(true);

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

        transferButton = new JButton();
        transferButton.setText("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 30));
        transferButton.setFocusable(false);
        transferButton.setIcon(rightArrow);
        transferButton.setHorizontalTextPosition(JButton.LEFT);
        transferButton.setVerticalTextPosition(JButton.CENTER);
        transferButton.setIconTextGap(10);
        transferButton.setForeground(Color.YELLOW);
        transferButton.setBackground(Color.BLUE);
        transferButton.setBorderPainted(false);
        transferButton.addActionListener(this);

        messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        messageLabel.setBackground(Color.BLUE);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVisible(false);


        panelLeft.setPreferredSize(new Dimension(250,300));
        panelRight.setPreferredSize(new Dimension(250,300));
        panelUp.setPreferredSize(new Dimension(100,100));
        panelDown.setPreferredSize(new Dimension(100,100));

        panelLeft.setBackground(Color.BLUE);
        panelRight.setBackground(Color.BLUE);
        panelUp.setBackground(Color.BLUE);
        panelDown.setBackground(Color.BLUE);

        panelUp.add(upperText);

        panelDown.add(messageLabel);

        panelLeft.add(backButton);

        panelRight.add(transferButton);

        JPanel panelCenterUp = new JPanel();
        JPanel panelCenterDown = new JPanel();
        JPanel panelCenterCenter = new JPanel();


        panelCenterUp.setPreferredSize(new Dimension(100,300));
        panelCenterDown.setPreferredSize(new Dimension(100,200));


        panelCenterUp.setBackground(Color.BLUE);
        panelCenterDown.setBackground(Color.BLUE);
        panelCenterCenter.setBackground(Color.BLUE);

        panelCenterCenter.add(transferUserLabel,BorderLayout.CENTER);
        panelCenterCenter.add(transferUserField,BorderLayout.CENTER);
        panelCenterCenter.add(amountLabel,BorderLayout.CENTER);
        panelCenterCenter.add(amountField,BorderLayout.CENTER);


        panelCenter.add(panelCenterUp,BorderLayout.NORTH);
        panelCenter.add(panelCenterDown,BorderLayout.SOUTH);
        panelCenter.add(panelCenterCenter,BorderLayout.CENTER);


        frame.add(panelCenter,BorderLayout.CENTER);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelRight,BorderLayout.EAST);
        frame.add(panelUp, BorderLayout.NORTH);
        frame.add(panelDown, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


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
        } else if (e.getSource() == transferButton) {
            try {
                Connect();
                targetUname = transferUserField.getText();
                pst = con.prepareStatement("select * from atmusers where username = ?");
                pst.setString(1,targetUname);

                resultSet = pst.executeQuery();
                if(resultSet.next()){
                    targetBallance = resultSet.getInt(4);
                    targetCurrency = resultSet.getString(5);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }

            try {
                amount = Integer.parseInt(amountField.getText());
                ballance = ballance - amount;
                targetBallance = targetBallance + amount;
                ballanceUpdate(uname,ballance);
                ballanceUpdate(targetUname,targetBallance);
                messageLabel.setText("You transfered " + amount + currency );
                messageLabel.setVisible(true);

            }catch (Exception ex){
                messageLabel.setText("Something wrong! Try again!");
                messageLabel.setVisible(true);
            }


        }
    }
}
