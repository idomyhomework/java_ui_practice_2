package com.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1 extends JFrame {
    public Ejercicio1(JFrame ven2) {
        setTitle("Ejercicio 1");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        JButton centerBtn = new JButton("Pulsame!");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);

        panel.add(centerBtn, gbc); // Pass gbc here

        // Open ven2 when button is clicked
        centerBtn.addActionListener(e -> {
            ven2.setVisible(true);
        });

        add(panel);
    }
}