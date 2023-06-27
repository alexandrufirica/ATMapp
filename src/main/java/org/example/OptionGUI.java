package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OptionGUI extends AuthentificationService implements ActionListener {
    JFrame frame;
    JPanel panelCenter;
    JLabel WithdrawalLimitLabel;
    JTextField WithdrawalLimitField;
    JButton backButton;
    JButton saveButton;
    JLabel messageLabel;
    JComboBox withdrawalLimitComboBox;

    String withdrawalLimit;

    public OptionGUI(){
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

        WithdrawalLimitLabel = new JLabel();
        WithdrawalLimitLabel.setText("Enter new withdrawal limit: ");
        WithdrawalLimitLabel.setHorizontalAlignment(JLabel.CENTER);
        WithdrawalLimitLabel.setVerticalAlignment(JLabel.CENTER);
        WithdrawalLimitLabel.setForeground(Color.YELLOW);
        WithdrawalLimitLabel.setFont(new Font("Arial", Font.PLAIN, 30));

//        WithdrawalLimitField = new JTextField();
//        WithdrawalLimitField.setPreferredSize(new Dimension(200,40));
//        WithdrawalLimitField.setOpaque(false);
//        WithdrawalLimitField.setFont(new Font("Arial", Font.PLAIN,25));
//        WithdrawalLimitField.setForeground(Color.YELLOW);
//        WithdrawalLimitField.setCaretColor(Color.YELLOW);
//        WithdrawalLimitField.setVisible(true);

        String []  withdrawalLimits = {"1000","2000","3000","4000","5000"};

        withdrawalLimitComboBox = new JComboBox(withdrawalLimits);
        withdrawalLimitComboBox.addActionListener(this);
        withdrawalLimitComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
        withdrawalLimitComboBox.setBackground(Color.BLUE);
        withdrawalLimitComboBox.setForeground(Color.YELLOW);
        withdrawalLimitComboBox.setOpaque(false);

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

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        saveButton.setFont(new Font("Arial", Font.PLAIN, 30));
        saveButton.setFocusable(false);
        saveButton.setIcon(rightArrow);
        saveButton.setHorizontalTextPosition(JButton.LEFT);
        saveButton.setVerticalTextPosition(JButton.CENTER);
        saveButton.setForeground(Color.YELLOW);
        saveButton.setBackground(Color.BLUE);
        saveButton.setBorderPainted(false);
        saveButton.addActionListener(this);

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

        panelRight.add(saveButton);

        JPanel panelCenterUp = new JPanel();
        JPanel panelCenterDown = new JPanel();
        JPanel panelCenterCenter = new JPanel();


        panelCenterUp.setPreferredSize(new Dimension(100,300));
        panelCenterDown.setPreferredSize(new Dimension(100,200));


        panelCenterUp.setBackground(Color.BLUE);
        panelCenterDown.setBackground(Color.BLUE);
        panelCenterCenter.setBackground(Color.BLUE);

        panelCenterCenter.add(WithdrawalLimitLabel,BorderLayout.CENTER);
        panelCenterCenter.add(withdrawalLimitComboBox,BorderLayout.CENTER);
//        panelCenterCenter.add(WithdrawalLimitField,BorderLayout.CENTER);

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

    public void WithdrawalLimitUpdata(String uname, String withdrawalLimit){
        String SQL = "UPDATE atmusers "
                + "SET withdrawalLimit = ? "
                + "WHERE username = ?";

        try (PreparedStatement pstmt = con.prepareStatement(SQL))  {

            pstmt.setString(1,withdrawalLimit);
            pstmt.setString(2,uname);

            pstmt.executeUpdate();

        }catch ( SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenuGUI();
        } else if (e.getSource() == saveButton) {
            withdrawalLimit = (String) withdrawalLimitComboBox.getSelectedItem();
            System.out.println(withdrawalLimit);
            messageLabel.setText("Changes saved!");
            messageLabel.setVisible(true);
        }
    }
}
