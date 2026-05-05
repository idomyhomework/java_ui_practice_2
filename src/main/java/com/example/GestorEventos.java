package com.example;

import java.awt.event.*;
import java.util.Random;

public class GestorEventos implements ActionListener {

    private Ejercicio6 ventana;
    private Random random = new Random();

    public GestorEventos(Ejercicio6 ventana) {
        this.ventana = ventana;
    }

    /**
     * @param obtiene la acción que acaba de ocurrir.
     *                Recibe acceso a la ventana al tráves del constructor, por eso
     *                tiene acceso a todos sus métodos y atributos.
     *                Hace acciones correspondientes con cada botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        // ── Botón Calcular ──
        if (fuente == ventana.btnCalcular) {
            int n;
            if (ventana.rbEntre0y100.isSelected())
                n = random.nextInt(101);
            else if (ventana.rbEntre100y200.isSelected())
                n = 100 + random.nextInt(101);
            else
                n = 200 + random.nextInt(301);
            ventana.lblResultado.setText(String.valueOf(n));

            // ── Botón Salir ──
        } else if (fuente == ventana.btnSalir) {
            System.exit(0);

            // ── Radio buttons animales ──
        } else if (fuente == ventana.rbPerro)
            ventana.mostrarImagen("Perro");
        else if (fuente == ventana.rbGato)
            ventana.mostrarImagen("Gato");
        else if (fuente == ventana.rbTigre)
            ventana.mostrarImagen("Tigre");
        else if (fuente == ventana.rbLeon)
            ventana.mostrarImagen("León");
    }
}