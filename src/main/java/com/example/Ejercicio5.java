package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ejercicio 12 – Juego de parejas con JToggleButton.
 *
 * Cartas: 2 ases, 2 reyes, 2 caballos, 2 sotas (8 en total, 4 parejas).
 *
 * Reglas:
 * - El jugador sólo puede voltear 2 cartas a la vez.
 * - Si forman pareja → desaparecen.
 * - Si no → vuelven bocabajo tras 1 segundo.
 * - Se cuentan los intentos y se muestra un mensaje al terminar.
 *
 * Imágenes:
 * - Coloca as.png / rey.png / caballo.png / sota.png / reverso.png
 * en src/main/resources/images/.
 * - Si no existen, se muestran textos (AS / REY / REINA / SOTA).
 */
public class Ejercicio5 extends JFrame {

    // ── Constantes ────────────────────────────────────────────────
    private static final String[] TIPOS = { "AS", "REY", "REINA", "SOTA" };
    private static final Color COLOR_REVERSO = new Color(178, 34, 34); // rojo oscuro
    private static final Color COLOR_FRENTE = new Color(255, 248, 220); // crema

    // ── Estado del juego ──────────────────────────────────────────
    private final String[] cartas = new String[8];
    private final JToggleButton[] botones = new JToggleButton[8];
    private int primeraSeleccion = -1; // índice de la primera carta volteada
    private int intentos = 0;
    private int paresEncontrados = 0;
    private boolean procesando = false; // bloqueo mientras anima el volteo

    // ── Componentes ───────────────────────────────────────────────
    private JLabel lblIntentos;

    public Ejercicio5() {
        setTitle("Adivina las parejas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        inicializarCartas();
        add(crearPanelCartas(), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    // ─────────────────────────────────────────────────────────────
    // Inicialización
    // ─────────────────────────────────────────────────────────────

    /** Crea las 8 cartas (2 de cada tipo) y las baraja. */
    private void inicializarCartas() {
        List<String> lista = new ArrayList<>();
        for (String tipo : TIPOS) {
            lista.add(tipo);
            lista.add(tipo);
        }
        Collections.shuffle(lista);
        lista.toArray(cartas);
    }

    /** Construye el panel 2×4 con los JToggleButton. */
    private JPanel crearPanelCartas() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));
        panel.setBackground(new Color(0, 100, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        for (int i = 0; i < 8; i++) {
            botones[i] = crearBotonCarta(i);
            panel.add(botones[i]);
        }
        return panel;
    }

    /** Crea un JToggleButton con el aspecto de carta bocabajo. */
    private JToggleButton crearBotonCarta(int idx) {
        JToggleButton btn = new JToggleButton();
        btn.setPreferredSize(new Dimension(95, 130));
        btn.setBackground(COLOR_REVERSO);
        btn.setIcon(cargarIcono("reverso"));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> manejarClic(idx));
        return btn;
    }

    /** Panel inferior con el contador de intentos. */
    private JPanel crearPanelInferior() {
        lblIntentos = new JLabel("Intentos: 0", SwingConstants.CENTER);
        lblIntentos.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
        panel.add(lblIntentos);
        return panel;
    }

    // ─────────────────────────────────────────────────────────────
    // Lógica del juego
    // ─────────────────────────────────────────────────────────────

    private void manejarClic(int idx) {
        // Ignorar clics mientras se procesa un par o si la carta ya está resuelta
        if (procesando || !botones[idx].isEnabled()) {
            botones[idx].setSelected(botones[idx].isSelected()); // cancelar toggle
            return;
        }

        voltearFrente(idx);

        if (primeraSeleccion == -1) {
            // Primera carta del turno
            primeraSeleccion = idx;
        } else {
            // Segunda carta del turno → comprobar pareja
            procesando = true;
            int primera = primeraSeleccion;
            int segunda = idx;
            primeraSeleccion = -1;

            intentos++;
            lblIntentos.setText("Intentos: " + intentos);

            if (cartas[primera].equals(cartas[segunda])) {
                // ¡Pareja! Ocultar tras breve pausa visual
                Timer timerOcultar = new Timer(500, e -> {
                    botones[primera].setVisible(false);
                    botones[segunda].setVisible(false);
                    paresEncontrados++;
                    procesando = false;
                    if (paresEncontrados == TIPOS.length)
                        mostrarEnhorabuena();
                });
                timerOcultar.setRepeats(false);
                timerOcultar.start();
            } else {
                // No es pareja → voltear bocabajo tras 1 segundo
                Timer timerVoltear = new Timer(1000, e -> {
                    voltearReverso(primera);
                    voltearReverso(segunda);
                    procesando = false;
                });
                timerVoltear.setRepeats(false);
                timerVoltear.start();
            }
        }
    }

    /** Muestra la cara de la carta (frente). */
    private void voltearFrente(int idx) {
        botones[idx].setBackground(COLOR_FRENTE);
        ImageIcon icono = cargarIcono(cartas[idx].toLowerCase());
        if (icono != null) {
            botones[idx].setIcon(icono);
            botones[idx].setText("");
        } else {
            botones[idx].setIcon(null);
            botones[idx].setText(cartas[idx]);
            botones[idx].setFont(new Font("Arial", Font.BOLD, 20));
        }
    }

    /** Vuelve la carta bocabajo (reverso). */
    private void voltearReverso(int idx) {
        botones[idx].setSelected(false);
        botones[idx].setBackground(COLOR_REVERSO);
        ImageIcon icono = cargarIcono("reverso");
        if (icono != null) {
            botones[idx].setIcon(icono);
            botones[idx].setText("");
        } else {
            botones[idx].setIcon(null);
            botones[idx].setText("");
        }
    }

    /** Muestra el diálogo de victoria. */
    private void mostrarEnhorabuena() {
        JOptionPane.showMessageDialog(
                this,
                "¡Enhorabuena! Has completado el juego en " + intentos + " intentos.",
                "¡Has ganado!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // ─────────────────────────────────────────────────────────────
    // Utilidades
    // ─────────────────────────────────────────────────────────────

    /**
     * Carga un ImageIcon escalado a 80×110 px desde /images/{nombre}.png.
     * Devuelve null si el recurso no existe (el botón usará texto).
     */
    private ImageIcon cargarIcono(String nombre) {
        java.net.URL url = getClass().getResource("/images/" + nombre + ".png");
        if (url == null)
            return null;
        ImageIcon original = new ImageIcon(url);
        Image escalada = original.getImage()
                .getScaledInstance(80, 110, Image.SCALE_SMOOTH);
        return new ImageIcon(escalada);
    }

}