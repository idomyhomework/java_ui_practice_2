package com.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio3 extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JRadioButton rbHombre;
    private JRadioButton rbMujer;
    private JCheckBox chkAcuerdo;
    private JButton btnEnviar;

    public Ejercicio3() {
        setTitle("Formulario – Ejercicio 10");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ── Nombre ──────────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtNombre, gbc);

        // ── Apellidos ────────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Apellidos:"), gbc);
        txtApellidos = new JTextField();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtApellidos, gbc);

        // ── Email ────────────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField();
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // ── Sexo ─────────────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Sexo:"), gbc);

        rbHombre = new JRadioButton("Hombre");
        rbMujer = new JRadioButton("Mujer");
        ButtonGroup bgSexo = new ButtonGroup();
        bgSexo.add(rbHombre);
        bgSexo.add(rbMujer);

        JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSexo.add(rbHombre);
        panelSexo.add(rbMujer);
        gbc.gridx = 1;
        panel.add(panelSexo, gbc);

        // ── Checkbox ─────────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        chkAcuerdo = new JCheckBox("Acepto los términos y condiciones");
        panel.add(chkAcuerdo, gbc);

        // ── Botón Enviar ─────────────────────────────────────────
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnEnviar = new JButton("Enviar");
        panel.add(btnEnviar, gbc);

        btnEnviar.addActionListener(e -> enviar());

        add(panel);
        setLocationRelativeTo(null);
    }

    private void enviar() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();

        String sexo = rbHombre.isSelected() ? "Hombre"
                : rbMujer.isSelected() ? "Mujer"
                        : "No seleccionado";

        String acuerdo = chkAcuerdo.isSelected() ? "SI" : "NO";

        String mensaje = "Nombre: " + nombre + "\n"
                + "Apellidos: " + apellidos + "\n"
                + "Email: " + email + "\n"
                + "Sexo: " + sexo + "\n"
                + "De acuerdo: " + acuerdo;

        JOptionPane.showMessageDialog(this, mensaje, "Datos del formulario",
                JOptionPane.INFORMATION_MESSAGE);

        // Desactivar todos los controles
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtEmail.setEnabled(false);
        rbHombre.setEnabled(false);
        rbMujer.setEnabled(false);
        chkAcuerdo.setEnabled(false);
        btnEnviar.setEnabled(false);
    }
}
