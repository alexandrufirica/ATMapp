package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BallanceGUI extends AppService implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel ballanceLabel;
    JButton backButton;
    JButton withdrawalButton;
    JButton depositButton;
    
    public BallanceGUI(){

        try {
            Connect();

            pst = con.prepareStatement("select * from atmusers where username = ?");
            pst.setString(1,uname);

            ResultSet resultSet;
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

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setTitle("ATMapp");
        
        panel = new JPanel();
        panel.setSize(1000,1000);
        panel.setLayout(new BorderLayout());
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

        ballanceLabel = new JLabel();
        ballanceLabel.setText("Your ballance is: " + ballance + currency);
        ballanceLabel.setHorizontalAlignment(JLabel.CENTER);
        ballanceLabel.setVerticalAlignment(JLabel.CENTER);
        ballanceLabel.setForeground(Color.YELLOW);
        ballanceLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        
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
        panelRight.add(depositButton);

        frame.add(panel);
        panel.add(ballanceLabel, BorderLayout.CENTER);
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRight,BorderLayout.EAST);
        panel.add(panelUp, BorderLayout.NORTH);
        panel.add(panelDown, BorderLayout.SOUTH);

        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new MainMenuGUI();
        }else if (e.getSource() == withdrawalButton) {
            frame.dispose();
            new WithdrawalGUI();

        } else if ( e.getSource() == depositButton) {
            frame.dispose();
            new DepositGUI();

        }
    }

}
