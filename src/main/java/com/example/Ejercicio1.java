package com.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1 extends JFrame {
    public Ejercicio1() {
        setTitle("Ejercicio 1");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        JButton centerBtn = new JButton("Pulsame!");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);

        panel.add(centerBtn, gbc);

        centerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "HOLA!");
        });

        add(panel);
    }
}