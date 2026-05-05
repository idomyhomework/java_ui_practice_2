package com.example;

import javax.swing.*;
import java.awt.*;

public class Ejercicio6 extends JFrame {
    // ----- Generador de números -----
    JRadioButton rbEntre0y100, rbEntre100y200, rbEntre200y500;
    JButton btnCalcular;
    JLabel lblResultado;

    // ----- Selector de animal -----
    JRadioButton rbPerro, rbGato, rbTigre, rbLeon;
    JLabel imagen;

    // ----- Salir -----
    JButton btnSalir;

    private static final String TOOLTIP_RANGO = "Seleccione opción y haga clic sobre el botón Calcular";

    public Ejercicio6() {
        setTitle("Ejercicio 6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 780);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        GestorEventos gestor = new GestorEventos(this);

        // ----- PANEL SUPERIOR – Generador de números ------

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ----- Etiqueta de la parte superior -----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelSuperior.add(new JLabel("Generador de números:"), gbc);

        // ----- Radio buttons de rango ------
        rbEntre0y100 = new JRadioButton("Entre 0 y 100", true);
        rbEntre100y200 = new JRadioButton("Entre 100 y 200");
        rbEntre200y500 = new JRadioButton("Entre 200 y 500");

        // ------ Tooltips (requisito 4) ------
        rbEntre0y100.setToolTipText(TOOLTIP_RANGO);
        rbEntre100y200.setToolTipText(TOOLTIP_RANGO);
        rbEntre200y500.setToolTipText(TOOLTIP_RANGO);

        // ----- Agrupar las botones para asegurar que solo uno puede estar seleccionado
        // -----
        ButtonGroup bgNumeros = new ButtonGroup();
        bgNumeros.add(rbEntre0y100);
        bgNumeros.add(rbEntre100y200);
        bgNumeros.add(rbEntre200y500);

        // ----- Disposicion visual -----
        JPanel panelRadiosNum = new JPanel(new GridLayout(3, 1, 2, 2));
        panelRadiosNum.add(rbEntre0y100);
        panelRadiosNum.add(rbEntre100y200);
        panelRadiosNum.add(rbEntre200y500);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelSuperior.add(panelRadiosNum, gbc);

        // ------ Botón Calcular ------
        btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(gestor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(btnCalcular, gbc);

        // ------ Label resultado ------
        lblResultado = new JLabel("0", SwingConstants.CENTER);
        lblResultado.setFont(new Font("SansSerif", Font.BOLD, 48));
        lblResultado.setPreferredSize(new Dimension(200, 80));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelSuperior.add(lblResultado, gbc);

        // ----- PANEL CENTRAL – Selector de imagen ------

        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.add(new JLabel("Elije una opción para ver la imagen", SwingConstants.CENTER), BorderLayout.NORTH);

        // ---- Botones Radio ----
        rbPerro = new JRadioButton("Perro", true);
        rbGato = new JRadioButton("Gato");
        rbTigre = new JRadioButton("Tigre");
        rbLeon = new JRadioButton("León");

        // ----- Registrar eventos de animales en el gestor -----
        rbPerro.addActionListener(gestor);
        rbGato.addActionListener(gestor);
        rbTigre.addActionListener(gestor);
        rbLeon.addActionListener(gestor);

        // ----- solo un botón puede estar seleccionado -----
        ButtonGroup bgAnimales = new ButtonGroup();
        bgAnimales.add(rbPerro);
        bgAnimales.add(rbGato);
        bgAnimales.add(rbTigre);
        bgAnimales.add(rbLeon);

        // ----- Disposicion visual -----
        JPanel panelRadiosAnim = new JPanel(new GridLayout(4, 1, 2, 5));
        panelRadiosAnim.add(rbPerro);
        panelRadiosAnim.add(rbGato);
        panelRadiosAnim.add(rbTigre);
        panelRadiosAnim.add(rbLeon);
        panelCentral.add(panelRadiosAnim, BorderLayout.WEST);

        imagen = new JLabel("", SwingConstants.CENTER);
        imagen.setPreferredSize(new Dimension(300, 300));
        imagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imagen.setOpaque(true);
        imagen.setBackground(new Color(230, 230, 230));
        panelCentral.add(imagen, BorderLayout.CENTER);
        mostrarImagen("Perro");

        // ----- PANEL INFERIOR – Botón Salir ------
        JPanel panelSalir = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(gestor);
        panelSalir.add(btnSalir);

        // ----- Añadir todo al frame -----
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelSalir, BorderLayout.SOUTH);
    }

    // ----- Accesible desde GestorEventos -----
    void mostrarImagen(String animal) {
        String ruta = switch (animal) {
            case "Perro" -> "/images/animales/perro.jpg";
            case "Gato" -> "/images/animales/gato.jpg";
            case "Tigre" -> "/images/animales/tigre.jpg";
            case "Leon" -> "/images/animales/leon.jpg";
            default -> "/images/animales/perro.jpg";
        };
        java.net.URL url = getClass().getResource(ruta);
        if (url != null) {
            Image img = new ImageIcon(url).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagen.setIcon(new ImageIcon(img));
            imagen.setText("");
        } else {
            imagen.setIcon(null);
            imagen.setText(
                    "<html><center><b>" + animal + "</b><br><small>(imagen no encontrada)</small></center></html>");
        }
    }
}
