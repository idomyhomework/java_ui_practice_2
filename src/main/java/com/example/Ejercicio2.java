package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ejercicio2 extends JFrame {
    public Ejercicio2() {
        setTitle("Ejercicio 2");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        JButton centerBtn = new JButton("Pulsame");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 1, 1, 1);

        panel.add(centerBtn, gbc);

        centerBtn.addActionListener(e -> {
            String modText = getModifiersText(e.getModifiers());
            String mensaje = "Objeto: " + e.getSource() + "\n"
                    + "Teclas: " + modText + "\n"
                    + "Comandos: " + e.getActionCommand();
            JOptionPane.showMessageDialog(this, mensaje);
        });

        add(panel);
    }

    private String getModifiersText(int modifiers) {
        StringBuilder sb = new StringBuilder();
        if ((modifiers & ActionEvent.ALT_MASK) != 0)
            sb.append("Alt ");
        if ((modifiers & ActionEvent.CTRL_MASK) != 0)
            sb.append("Ctrl ");
        if ((modifiers & ActionEvent.SHIFT_MASK) != 0)
            sb.append("Shift ");
        return sb.length() > 0 ? sb.toString().trim() : "Ninguna";
    }
}