package com.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1INFO extends JFrame {
    public Ejercicio1INFO() {
        setTitle("Message");
        setSize(250, 130);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Icono de información
        JLabel icono = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 5);
        panel.add(icono, gbc);

        // Texto HOLA
        JLabel texto = new JLabel("HOLA");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 10, 10);
        panel.add(texto, gbc);

        // Botón OK
        JButton okBtn = new JButton("OK");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 10, 5);
        panel.add(okBtn, gbc);

        // Cerrar ventana al pulsar OK
        okBtn.addActionListener(e -> dispose());

        add(panel);
        setLocationRelativeTo(null);
        setVisible(false); // Empieza oculta
    }
}
