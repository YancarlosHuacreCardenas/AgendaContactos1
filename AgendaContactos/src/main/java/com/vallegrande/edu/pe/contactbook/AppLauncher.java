package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;  // Importación de Swing

public class AppLauncher {

    // Método principal que inicia la aplicación
    public static void main(String[] args) {
        System.out.println("Método main ejecutado");

        // Se asegura de que la GUI se ejecute en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Se crea una instancia de la ventana principal y se hace visible
                new ContactManagerApp();
            }
        });
    }
}