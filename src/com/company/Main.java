package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main{
    private int num;
    private JLabel amountLabel = new JLabel(String.valueOf(num));
    private JLabel secLabel = new JLabel();
    Act act = new Act();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
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
        shipPanel.setBounds(100,170,200,200);
        shipPanel.setOpaque(false);
//        shipPanel.setBackground(Color.white);
        frame.add(shipPanel);

        ImageIcon ship = new ImageIcon("picture/purpleufo.png");

        JButton button = new JButton();
        shipPanel.setFont(font);
        button.addActionListener(act);
        button.setIcon(ship);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        shipPanel.add(button);

        amountLabel = new JLabel(String.valueOf(num));
        amountLabel.setFont(font);
        //amountLabel.setBackground(Color.black);
        amountLabel.setLayout(new GridLayout(2, 1));
        amountLabel.setBounds(50,50,100,100);
        secLabel = new JLabel();
        frame.add(amountLabel);
        frame.setVisible(true);
        amountLabel.setForeground(Color.white);
    }


    class Act implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num++;
            amountLabel.setText(String.valueOf(num));
        }
    }

    static class BgPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image img = null;
            try{
                img = ImageIO.read(new File("picture/back.png"));
            }
            catch(IOException ignore){
            }
                g.drawImage(img, 0, 0, null);
        }
    }
}