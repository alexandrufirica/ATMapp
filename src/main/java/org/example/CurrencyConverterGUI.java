package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyConverterGUI extends  AppService implements ActionListener, KeyListener {
    JFrame frame;
    JPanel panelCenter;
    JButton backButton;
    JButton convertButton;
    JComboBox fromCurrencyComboBox;
    JComboBox toCurrencyComboBox;
    JTextField fromTextField;
    JTextField toTextField;
    JLabel messageLabel;

    double USDEUR;
    double USDGBP;
    double USDCAD;
    double USDRON;

    double fromAmount;
    double toAmount;

    public CurrencyConverterGUI(){

            ResultSet resultSet;

            try {
                ConnectCurrencyRates();

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
                String todayDate = LocalDateTime.now().format(dateFormat).toString();

                pst = con.prepareStatement("select * from rates where date = ?");
                pst.setString(1,todayDate);

                resultSet = pst.executeQuery();

                while(resultSet.next()){
                    USDEUR = resultSet.getDouble(2);
                    USDGBP = resultSet.getDouble(3);
                    USDCAD = resultSet.getDouble(4);
                    USDRON = resultSet.getDouble(5);
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

            convertButton = new JButton();
            convertButton.setText("Convert");
            convertButton.setFont(new Font("Arial", Font.PLAIN, 30));
            convertButton.setFocusable(false);
            convertButton.setIcon(rightArrow);
            convertButton.setHorizontalTextPosition(JButton.LEFT);
            convertButton.setVerticalTextPosition(JButton.CENTER);
            convertButton.setIconTextGap(10);
            convertButton.setForeground(Color.YELLOW);
            convertButton.setBackground(Color.BLUE);
            convertButton.setBorderPainted(false);
            convertButton.addActionListener(this);

            fromTextField = new JTextField();
            fromTextField.setPreferredSize(new Dimension(300,40));
            fromTextField.setOpaque(false);
            fromTextField.setFont(new Font("Arial", Font.PLAIN,15));
            fromTextField.setForeground(Color.YELLOW);
            fromTextField.setCaretColor(Color.YELLOW);
            fromTextField.setVisible(true);
            fromTextField.addKeyListener(this);


            toTextField = new JTextField();
            toTextField.setPreferredSize(new Dimension(300,40));
            toTextField.setOpaque(false);
            toTextField.setFont(new Font("Arial", Font.PLAIN,15));
            toTextField.setForeground(Color.YELLOW);
            toTextField.setCaretColor(Color.YELLOW);
            toTextField.setVisible(true);
            toTextField.setEditable(false);
            toTextField.addKeyListener(this);
                

            String []  currencys = {"USD","GBP","EUR","CAD","RON"};
    
            fromCurrencyComboBox = new JComboBox(currencys);
            fromCurrencyComboBox.addActionListener(this);
            fromCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
            fromCurrencyComboBox.setBackground(Color.BLUE);
            fromCurrencyComboBox.setForeground(Color.YELLOW);
            fromCurrencyComboBox.setOpaque(false);

            toCurrencyComboBox = new JComboBox(currencys);
            toCurrencyComboBox.addActionListener(this);
            toCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 30));
            toCurrencyComboBox.setBackground(Color.BLUE);
            toCurrencyComboBox.setForeground(Color.YELLOW);
            toCurrencyComboBox.setOpaque(false);

            messageLabel = new JLabel();
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            messageLabel.setBackground(Color.BLUE);
            messageLabel.setForeground(Color.YELLOW);
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            messageLabel.setVisible(false);
            panelDown.add(messageLabel);

            panelLeft.setPreferredSize(new Dimension(200,300));
            panelRight.setPreferredSize(new Dimension(200,300));
            panelUp.setPreferredSize(new Dimension(100,100));
            panelDown.setPreferredSize(new Dimension(100,100));

            panelLeft.setBackground(Color.BLUE);
            panelRight.setBackground(Color.BLUE);
            panelUp.setBackground(Color.BLUE);
            panelDown.setBackground(Color.BLUE);

            panelUp.add(upperText);

            panelLeft.add(backButton);

            panelRight.add(convertButton);

            JPanel panelCenterUp = new JPanel();
            JPanel panelCenterDown = new JPanel();
            JPanel panelCenterCenter = new JPanel();


            panelCenterUp.setPreferredSize(new Dimension(100,300));
            panelCenterDown.setPreferredSize(new Dimension(100,300));

            panelCenterUp.setBackground(Color.BLUE);
            panelCenterDown.setBackground(Color.BLUE);
            panelCenterCenter.setBackground(Color.BLUE);

            panelCenterCenter.add(fromTextField,BorderLayout.CENTER);
            panelCenterCenter.add(fromCurrencyComboBox,BorderLayout.CENTER);
            panelCenterCenter.add(toTextField,BorderLayout.CENTER);
            panelCenterCenter.add(toCurrencyComboBox,BorderLayout.CENTER);

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



    public void update(){

        double rate = 0;

        switch (fromCurrencyComboBox.getSelectedItem().toString() + toCurrencyComboBox.getSelectedItem().toString()){

            //USD rates
            case "USDUSD" : rate = 1; break;
            case "USDEUR" : rate = USDEUR; break;
            case "USDGBP" : rate = USDGBP; break;
            case "USDCAD" : rate = USDCAD; break;
            case "USDRON" : rate = USDRON; break;

            //EUR rates
            case "EUREUR" : rate = 1; break;
            case "EURUSD" : rate = 1/USDEUR; break;
            case "EURGBP" : rate = 1/USDEUR * USDGBP; break;
            case "EURCAD" : rate = 1/USDEUR * USDCAD; break;
            case "EURRON" : rate = 1/USDEUR * USDRON; break;

            //GBP rates
            case "GBPGBP" : rate = 1; break;
            case "GBPUSD" : rate = 1/USDGBP; break;
            case "GBPEUR" : rate = 1/USDGBP * USDEUR; break;
            case "GBPCAD" : rate = 1/USDGBP * USDCAD; break;
            case "GBPRON" : rate = 1/USDGBP * USDRON; break;

            //CAD rates
            case "CADCAD" : rate = 1; break;
            case "CADUSD" : rate = 1/USDCAD; break;
            case "CADEUR" : rate = 1/USDCAD * USDEUR; break;
            case "CADGBP" : rate = 1/USDCAD * USDGBP; break;
            case "CADRON" : rate = 1/USDCAD * USDRON; break;

            //RON rates
            case "RONRON" : rate = 1; break;
            case "RONUSD" : rate = 1/USDRON; break;
            case "RONEUR" : rate = 1/USDRON * USDEUR; break;
            case "RONGBP" : rate = 1/USDRON * USDGBP; break;
            case "RONCAD" : rate = 1/USDRON * USDCAD; break;

        }

        toAmount = fromAmount * rate;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            frame.dispose();
            new MainMenuGUI();
        } else if ( e.getSource() == convertButton) {
            try {
                fromAmount = Double.parseDouble(fromTextField.getText());
                update();
                toTextField.setText(""+toAmount);
                messageLabel.setVisible(false);

            }catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number!");
                messageLabel.setVisible(true);
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
