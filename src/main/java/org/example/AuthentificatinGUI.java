package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;

public class AuthentificatinGUI extends AppService implements ActionListener, KeyListener {

    JFrame frame;
    JTextField userNameField;
    JPasswordField passwordField;
    JPanel panel;
    JLabel labelUsername;
    JLabel labelPassword;
    JButton submitButton;
    JLabel wrongLabel;
    JPanel panelUp ;
    JPanel panelDown;
    JPanel panelRight;
    JPanel panelLeft;

     AuthentificatinGUI(){

         panel = new JPanel();
         frame = new JFrame();
         userNameField = new JTextField();
         labelUsername = new JLabel();
         passwordField = new JPasswordField();
         labelPassword = new JLabel();
         wrongLabel = new JLabel();
         panelUp = new JPanel();
         panelDown = new JPanel();
         panelRight = new JPanel();
         panelLeft = new JPanel();

         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(1000,1000);
         frame.setResizable(false);
         frame.setLayout(new BorderLayout());
         frame.setTitle("ATMapp");
         frame.addKeyListener(this);

         panel.setLayout(new FlowLayout(FlowLayout.CENTER));
         panel.setBackground(Color.BLUE);

         userNameField.setPreferredSize(new Dimension(200,30));
         userNameField.setOpaque(false);
         userNameField.setFont(new Font("Arial", Font.PLAIN,15));
         userNameField.setForeground(Color.YELLOW);
         userNameField.setCaretColor(Color.YELLOW);
         userNameField.setVisible(true);
         userNameField.addKeyListener(this);


         passwordField.setPreferredSize(new Dimension(150,30));
         passwordField.setOpaque(false);
         passwordField.setFont(new Font("Arial", Font.PLAIN,30));
         passwordField.setForeground(Color.YELLOW);
         passwordField.setCaretColor(Color.YELLOW);
         passwordField.setVisible(true);
         passwordField.addKeyListener(this);

         labelUsername.setText("UserName: ");
         labelUsername.setHorizontalAlignment(JLabel.CENTER);
         labelUsername.setVerticalAlignment(JLabel.CENTER);
         labelUsername.setForeground(Color.YELLOW);
         labelUsername.setFont(new Font("Arial", Font.PLAIN, 15));
         labelUsername.add(userNameField);

         labelPassword.setText("PIN Code (4 digits): ");
         labelPassword.setHorizontalAlignment(JLabel.CENTER);
         labelPassword.setVerticalAlignment(JLabel.CENTER);
         labelPassword.setForeground(Color.YELLOW);
         labelPassword.setFont(new Font("Arial", Font.PLAIN, 15));
         labelPassword.add(passwordField);

         wrongLabel.setText("Something wrong! Try again!");
         wrongLabel.setFont(new Font("Arial", Font.PLAIN, 20));
         wrongLabel.setBackground(Color.BLUE);
         wrongLabel.setForeground(Color.YELLOW);
         wrongLabel.setVisible(false);
         panelDown.add(wrongLabel);

         submitButton = new JButton();
         submitButton.setText("Submit");
         submitButton.setFont(new Font("Arial", Font.PLAIN, 30));
         submitButton.setFocusable(false);
         submitButton.setForeground(Color.YELLOW);
         submitButton.setBackground(Color.BLUE);
         submitButton.setBorderPainted(false);
         submitButton.addActionListener(this);


         panel.add(labelUsername);
         panel.add(userNameField);
         panel.add(labelPassword);
         panel.add(passwordField);
         panel.add(submitButton);

         panelUp.setBackground(Color.BLUE);
         panelDown.setBackground(Color.BLUE);
         panelRight.setBackground(Color.BLUE);
         panelLeft.setBackground(Color.BLUE);

         panelUp.setPreferredSize(new Dimension(300,400));
         panelDown.setPreferredSize(new Dimension(300,400));
         panelRight.setPreferredSize(new Dimension(300,300));
         panelLeft.setPreferredSize(new Dimension(300,300));

         frame.setVisible(true);
         frame.add(panel, BorderLayout.CENTER);
         frame.add(panelUp, BorderLayout.NORTH);
         frame.add(panelDown, BorderLayout.SOUTH);
         frame.add(panelRight, BorderLayout.WEST);
         frame.add(panelLeft, BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

         if(e.getSource() == submitButton){
             try {
                 ConnectATMDB();
                  uname = userNameField.getText();
                  pass = passwordField.getText();

                 pst = con.prepareStatement("select * from atmusers where username = ? and password = ?");
                 pst.setString(1,uname);
                 pst.setString(2,pass);

                 ResultSet resultSet;
                 resultSet = pst.executeQuery();
                 if(resultSet.next()){
                     frame.dispose();
                     new MainMenuGUI();
                 }else{
                    wrongLabel.setVisible(true);
                 }

             }catch (Exception ex){
                ex.printStackTrace();
             }

         }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10){
            try {
                ConnectATMDB();
                uname = userNameField.getText();
                pass = passwordField.getText();

                pst = con.prepareStatement("select * from atmusers where username = ? and password = ?");
                pst.setString(1,uname);
                pst.setString(2,pass);

                ResultSet resultSet;
                resultSet = pst.executeQuery();
                if(resultSet.next()){
                    frame.dispose();
                    new MainMenuGUI();
                }else{
                    wrongLabel.setVisible(true);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
