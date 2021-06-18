package com.company;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ship {

    private int num;
    private int allNum;
    private int speedTimer;
    private double perSec;
    private boolean timerOn;
    Font font1;
    Act act = new Act();
    private JLabel amountLabel = new JLabel(String.valueOf(num));
    private final JLabel nextShip = new JLabel("", SwingConstants.CENTER);
    private final static ArrayList<String> ships = new ArrayList<>();
    private JLabel secLabel = new JLabel();

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton shipButton;

    private int price1 = 100;
    private int price2 = 500;
    private int price3 = 1000;
    private int price4 = 5000;
    private int curShip = 0;
    private int costNext = 250;
    Timer timer;

    public static void main(String[] args) {
        new Ship();
    }

    public Ship() {
        ships.add("picture/blueufo.png");
        ships.add("picture/pinkufo.png");
        ships.add("picture/purpleufo.png");
        ships.add("picture/yellowflo.png");
        ships.add("picture/greenflo.png");
        ships.add("picture/greenship.png");
        ships.add("picture/yellowship.png");
        timerOn = false;
        perSec = 0;
        num = 0;
        createUI();
    }

    public void createUI() {

        Font font = new Font("Arial", Font.ITALIC, 24);
        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setContentPane(new BgPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);

        JPanel shipPanel = new JPanel();
        shipPanel.setBounds(100, 170, 200, 200);
        shipPanel.setOpaque(false);

        frame.add(shipPanel);

        ImageIcon ship = new ImageIcon("picture/purpleufo.png");

        shipButton = new JButton();
        shipPanel.setFont(font);
        shipButton.addActionListener(act);
        shipButton.setIcon(ship);
        shipButton.setOpaque(false);
        shipButton.setContentAreaFilled(false);
        shipButton.setBorderPainted(false);
        shipButton.setBorder(null);
        shipButton.setActionCommand("fuel");
        shipPanel.add(shipButton);

        amountLabel = new JLabel(String.valueOf(num));
        amountLabel.setFont(font);
        amountLabel.setLayout(new GridLayout(2, 1));
        amountLabel.setBounds(50, 50, 100, 100);
        amountLabel.setForeground(Color.white);
        frame.add(amountLabel);
        frame.setVisible(true);

        secLabel = new JLabel();
        secLabel.setForeground(Color.white);
        amountLabel.add(secLabel);

        JPanel bonusPanel = new JPanel();
        bonusPanel.setBounds(360, 110, 250, 250);
        bonusPanel.setLayout(new GridLayout(4, 1));
        frame.add(bonusPanel);
        bonusPanel.setBackground(Color.black);

        button1 = new JButton("snail with a bucket (" + price1 + ")");
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(act);
        button1.setActionCommand("snail with a bucket");
        bonusPanel.add(button1);

        button2 = new JButton("???");
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(act);
        button2.setActionCommand("refueller");
        bonusPanel.add(button2);

        button3 = new JButton("???");
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(act);
        button3.setActionCommand("automatic refueling");
        bonusPanel.add(button3);

        button4 = new JButton("???");
        button4.setFont(font1);
        button4.setFocusPainted(false);
        button4.addActionListener(act);
        button4.setActionCommand("petrol monster");
        bonusPanel.add(button4);

        nextShip.setText("next " + costNext);
        nextShip.setBounds(100, 380, 200, 30);
        nextShip.setFont(font);
        nextShip.setForeground(Color.white);
        nextShip.setOpaque(false);
        frame.add(nextShip);

    }

    public void secTimer() {
        timer = new Timer(speedTimer, e -> {
            allNum++;
            num++;
            amountLabel.setText(String.valueOf(num));
        });
    }

    public void newTimer() {

        final int coeff = 1000;

        if (!timerOn) {
            timerOn = true;
        } else {
            timer.stop();
        }

        double speed = 1 / perSec * coeff;
        speedTimer = (int) Math.round(speed);
        String spT = String.format("%.1f", perSec);
        secLabel.setText(spT + "sec");
        secLabel.setForeground(Color.white);

        secTimer();
        timer.start();

    }


    class Act implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            final double markupSec1 = 0.1;
            final double markupSec2 = 0.2;
            final double markupSec3 = 0.5;
            final double markupSec4 = 1;
            final int markup1 = 10;
            final int markup2 = 50;
            final int markup3 = 200;
            final int markup4 = 500;

            String action = e.getActionCommand();

            switch (action) {
                case "fuel":

                    allNum++;
                    num++;
                    amountLabel.setText(String.valueOf(num));

                    if (num >= price2) {
                        button2.setText("refueller (" + price2 + ")");
                    }

                    if (num >= price3) {
                        button3.setText("automatic refueling (" + price3 + ")");
                    }

                    if (num >= price4) {
                        button4.setText("petrol monster (" + price4 + ")");
                    }

                    if (allNum >= costNext) {
                        if (curShip + 1 == Ship.ships.size()) {
                            shipButton.setText("You are a winner!!!");
                        }
                        curShip++;
                        amountLabel.setText("" + num);
                        costNext += costNext;
                        nextShip.setText("next in " + costNext);
                        shipButton.setIcon(new ImageIcon(ships.get(curShip)));
                    }
                    break;
                case "snail with a bucket":

                    if (num >= price1) {
                        num -= price1;
                        price1 += markup1;
                        button1.setText("snail with a bucket (" + price1 + ")");
                        amountLabel.setText(String.valueOf(num));
                        perSec = perSec + markupSec1;
                        newTimer();
                        break;
                    }

                case "refueller":
                    if (num >= price2) {
                        num -= price2;
                        price2 += markup2;
                        button2.setText("refueller (" + price2 + ")");
                        amountLabel.setText(String.valueOf(num));
                        perSec = perSec + markupSec2;
                        newTimer();
                        break;
                    }

                case "automatic refueling":
                    if (num >= price3) {
                        num -= price3;
                        price3 += markup3;
                        button3.setText("automatic refueling (" + price3 + ")");
                        amountLabel.setText(String.valueOf(num));
                        perSec = perSec + markupSec3;
                        newTimer();
                        break;
                    }

                case "petrol monster":
                    if (num >= price4) {
                        num -= price4;
                        price4 += markup4;
                        button4.setText("petrol monster (" + price4 + ")");
                        amountLabel.setText(String.valueOf(num));
                        perSec = perSec + markupSec4;
                        newTimer();
                        break;
                    }
                    break;
            }
        }
    }

    static class BgPanel extends JPanel {
        public void paintComponent(Graphics graphics) {
            Image img = null;
            try {
                img = ImageIO.read(new File("picture/back.png"));
            } catch (IOException ignore) {
            }
            graphics.drawImage(img, 0, 0, null);
        }
    }
}