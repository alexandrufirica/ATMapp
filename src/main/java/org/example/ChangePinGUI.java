package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePinGUI extends AppService implements ActionListener {
    JFrame frame;
    JPanel panelCenter;
    JLabel currentPinLabel;
    JButton backButton;
    JButton changePinButton;
    JTextField currentPinField;
    JLabel messageLabel;
    JLabel newPinLabel;
    JTextField newPinField;

    ResultSet resultSet;
    String newPass;
    public ChangePinGUI (){

        try {
            ConnectATMDB();
            pst = con.prepareStatement("select * from atmusers where username = ?");
            pst.setString(1,uname);

            resultSet = pst.executeQuery();
            if(resultSet.next()){
                pass = resultSet.getString(3);
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

        currentPinLabel = new JLabel();
        currentPinLabel.setText("Enter old PIN: ");
        currentPinLabel.setHorizontalAlignment(JLabel.CENTER);
        currentPinLabel.setVerticalAlignment(JLabel.CENTER);
        currentPinLabel.setForeground(Color.YELLOW);
        currentPinLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        currentPinField = new JTextField();
        currentPinField.setPreferredSize(new Dimension(200,40));
        currentPinField.setOpaque(false);
        currentPinField.setFont(new Font("Arial", Font.PLAIN,25));
        currentPinField.setForeground(Color.YELLOW);
        currentPinField.setCaretColor(Color.YELLOW);
        currentPinField.setVisible(true);

        newPinLabel = new JLabel();
        newPinLabel.setText("Enter new PIN: ");
        newPinLabel.setHorizontalAlignment(JLabel.CENTER);
        newPinLabel.setVerticalAlignment(JLabel.CENTER);
        newPinLabel.setForeground(Color.YELLOW);
        newPinLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        newPinField = new JTextField();
        newPinField.setPreferredSize(new Dimension(200,40));
        newPinField.setOpaque(false);
        newPinField.setFont(new Font("Arial", Font.PLAIN,25));
        newPinField.setForeground(Color.YELLOW);
        newPinField.setCaretColor(Color.YELLOW);
        newPinField.setVisible(true);

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

        changePinButton = new JButton();
        changePinButton.setText("Change PIN");
        changePinButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        changePinButton.setFont(new Font("Arial", Font.PLAIN, 30));
        changePinButton.setFocusable(false);
        changePinButton.setIcon(rightArrow);
        changePinButton.setHorizontalTextPosition(JButton.LEFT);
        changePinButton.setVerticalTextPosition(JButton.CENTER);
        changePinButton.setForeground(Color.YELLOW);
        changePinButton.setBackground(Color.BLUE);
        changePinButton.setBorderPainted(false);
        changePinButton.addActionListener(this);

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

        panelRight.add(changePinButton);

        JPanel panelCenterUp = new JPanel();
        JPanel panelCenterDown = new JPanel();
        JPanel panelCenterCenter = new JPanel();


        panelCenterUp.setPreferredSize(new Dimension(100,300));
        panelCenterDown.setPreferredSize(new Dimension(100,200));


        panelCenterUp.setBackground(Color.BLUE);
        panelCenterDown.setBackground(Color.BLUE);
        panelCenterCenter.setBackground(Color.BLUE);

        panelCenterCenter.add(currentPinLabel,BorderLayout.CENTER);
        panelCenterCenter.add(currentPinField,BorderLayout.CENTER);
        panelCenterCenter.add(newPinLabel,BorderLayout.CENTER);
        panelCenterCenter.add(newPinField,BorderLayout.CENTER);

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

    public void pinUpdate(String uname, String pin){
        String SQL = "UPDATE atmusers "
                + "SET password = ? "
                + "WHERE username = ?";

        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setString(1,pin);
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
        } else if (e.getSource() == changePinButton) {
            try {
                if (pass.equals(currentPinField.getText()) ){
                    newPass = newPinField.getText();
                    pass = newPass;
                    pinUpdate(uname, pass);
                    messageLabel.setText("You changed your PIN code!");
                    messageLabel.setVisible(true);
                }else {
                    messageLabel.setText("Old pin incorrect! Try again!");
                    messageLabel.setVisible(true);
                }
            }catch (Exception ex){
                messageLabel.setText("Something wrong! Try again!");
                messageLabel.setVisible(true);
            }

        }
    }
}
