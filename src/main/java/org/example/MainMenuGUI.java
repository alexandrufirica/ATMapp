package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends AuthentificationService implements ActionListener {

    JFrame frame = new JFrame();
    JButton ballanceButton;
    JButton transferButton;
    JButton withdrawalButton;
    JButton paymentButton;
    JButton depositButton;
    JButton changePinButton;
    JButton recentTransactionButton;
    JButton optionsButton;

    public  MainMenuGUI(){

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new GridLayout(5,1));

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(5,1));

        JPanel panelUp = new JPanel();

        JPanel panelDown = new JPanel();
        panelDown.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(frame.getSize());
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
        
        JLabel lowerText = new JLabel();
        lowerText.setText("This account is under bank security service! If this isn't you bank account please exit immediately!");
        lowerText.setFont(new Font("Arial", Font.PLAIN, 20));
        lowerText.setForeground(Color.yellow);
        lowerText.setBackground(Color.BLUE);
        lowerText.setOpaque(false);
        lowerText.setVerticalAlignment(JLabel.BOTTOM);
        lowerText.setHorizontalAlignment(JLabel.CENTER);
        lowerText.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("ATMapp");
        
        ballanceButton= new JButton();
        ballanceButton.setText("Ballance");
        ballanceButton.setFont(new Font("Arial", Font.PLAIN, 30));
        ballanceButton.setFocusable(false);
        ballanceButton.setIcon(leftArrow);
        ballanceButton.setHorizontalTextPosition(JButton.RIGHT);
        ballanceButton.setVerticalTextPosition(JButton.CENTER);
        ballanceButton.setIconTextGap(10);
        ballanceButton.setForeground(Color.YELLOW);
        ballanceButton.setBackground(Color.BLUE);
        ballanceButton.setBorderPainted(false);
        ballanceButton.addActionListener(this);

        transferButton= new JButton();
        transferButton.setText("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 30));
        transferButton.setFocusable(false);
        transferButton.setIcon(leftArrow);
        transferButton.setHorizontalTextPosition(JButton.RIGHT);
        transferButton.setVerticalTextPosition(JButton.CENTER);
        transferButton.setIconTextGap(10);
        transferButton.setForeground(Color.YELLOW);
        transferButton.setBackground(Color.BLUE);
        transferButton.setBorderPainted(false);
        transferButton.addActionListener(this);

        depositButton= new JButton();
        depositButton.setText("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 30));
        depositButton.setFocusable(false);
        depositButton.setIcon(leftArrow);
        depositButton.setHorizontalTextPosition(JButton.RIGHT);
        depositButton.setVerticalTextPosition(JButton.CENTER);
        depositButton.setIconTextGap(10);
        depositButton.setForeground(Color.YELLOW);
        depositButton.setBackground(Color.BLUE);
        depositButton.setBorderPainted(false);
        depositButton.addActionListener(this);

        changePinButton= new JButton();
        changePinButton.setText("Change PIN");
        changePinButton.setFont(new Font("Arial", Font.PLAIN, 30));
        changePinButton.setFocusable(false);
        changePinButton.setIcon(leftArrow);
        changePinButton.setHorizontalTextPosition(JButton.RIGHT);
        changePinButton.setVerticalTextPosition(JButton.CENTER);
        changePinButton.setIconTextGap(10);
        changePinButton.setForeground(Color.YELLOW);
        changePinButton.setBackground(Color.BLUE);
        changePinButton.setBorderPainted(false);
        changePinButton.addActionListener(this);

        withdrawalButton= new JButton();
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

        paymentButton= new JButton();
        paymentButton.setText("Payment");
        paymentButton.setFont(new Font("Arial", Font.PLAIN, 30));
        paymentButton.setFocusable(false);
        paymentButton.setIcon(rightArrow);
        paymentButton.setHorizontalTextPosition(JButton.LEFT);
        paymentButton.setVerticalTextPosition(JButton.CENTER);
        paymentButton.setIconTextGap(10);
        paymentButton.setForeground(Color.YELLOW);
        paymentButton.setBackground(Color.BLUE);
        paymentButton.setBorderPainted(false);
        paymentButton.addActionListener(this);

        recentTransactionButton= new JButton();
        recentTransactionButton.setText("Recent Transaction");
        recentTransactionButton.setFont(new Font("Arial", Font.PLAIN, 30));
        recentTransactionButton.setFocusable(false);
        recentTransactionButton.setIcon(rightArrow);
        recentTransactionButton.setHorizontalTextPosition(JButton.LEFT);
        recentTransactionButton.setVerticalTextPosition(JButton.CENTER);
        recentTransactionButton.setForeground(Color.YELLOW);
        recentTransactionButton.setBackground(Color.BLUE);
        recentTransactionButton.setBorderPainted(false);
        recentTransactionButton.addActionListener(this);

        optionsButton= new JButton();
        optionsButton.setText("Options");
        optionsButton.setFont(new Font("Arial", Font.PLAIN, 30));
        optionsButton.setFocusable(false);
        optionsButton.setIcon(rightArrow);
        optionsButton.setHorizontalTextPosition(JButton.LEFT);
        optionsButton.setVerticalTextPosition(JButton.CENTER);
        optionsButton.setIconTextGap(10);
        optionsButton.setForeground(Color.YELLOW);
        optionsButton.setBackground(Color.BLUE);
        optionsButton.setBorderPainted(false);
        optionsButton.addActionListener(this);
        
        panelLeft.setBackground(Color.BLUE);
        panelRight.setBackground(Color.BLUE);
        panelUp.setBackground(Color.BLUE);
        panelDown.setBackground(Color.BLUE);

        panelLeft.setPreferredSize(new Dimension(350,300));
        panelRight.setPreferredSize(new Dimension(350,300));
        panelUp.setPreferredSize(new Dimension(100,100));
        panelDown.setPreferredSize(new Dimension(100,100));

        panelUp.add(upperText);
        panelDown.add(lowerText,BorderLayout.SOUTH);

        panelLeft.add(ballanceButton);
        panelLeft.add(transferButton);
        panelLeft.add(depositButton);
        panelLeft.add(changePinButton);

        panelRight.add(withdrawalButton);
        panelRight.add(paymentButton);
        panelRight.add(recentTransactionButton);
        panelRight.add(optionsButton);
        

        frame.add(panel);
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRight,BorderLayout.EAST);
        panel.add(panelUp, BorderLayout.NORTH);
        panel.add(panelDown, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ballanceButton){
            frame.dispose();
            new BallanceGUI();
        }else if (e.getSource() == withdrawalButton){
            frame.dispose();
            new WithdrawalGUI();
        } else if (e.getSource() == depositButton) {
            frame.dispose();
            new DepositGUI();
        } else if (e.getSource() == transferButton) {
            frame.dispose();
            new TransferGUI();
        } else if (e.getSource() == changePinButton) {
            frame.dispose();
            new ChangePinGUI();
        } else if (e.getSource() == optionsButton) {
            frame.dispose();
            new OptionGUI();
        }

    }
}
