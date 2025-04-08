package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import java.awt.*;

// Clase principal que extiende JFrame y representa la ventana principal de la aplicación
public class ContactManagerApp extends JFrame {

    // Constructor de la clase
    public ContactManagerApp() {
        System.out.println("Constructor de ContactManagerApp ejecutado");

        // Título de la ventana
        setTitle("Gestión de Contactos");
        // Tamaño de la ventana (ancho x alto)
        setSize(600, 400);
        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);
        // Finaliza la aplicación al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación de los paneles de la aplicación
        ContactListPanel contactListPanel = new ContactListPanel();     // Panel central con la lista de contactos
        ContactInputPanel contactInputPanel = new ContactInputPanel(); // Panel inferior para ingresar nuevos contactos
        ContactInfoPanel contactInfoPanel = new ContactInfoPanel();     // Panel superior con información general

        // Se establece un layout de tipo BorderLayout para organizar los paneles
        setLayout(new BorderLayout());

        // Se agregan los paneles al frame en sus respectivas posiciones
        add(contactInfoPanel, BorderLayout.NORTH);   // Parte superior
        add(contactListPanel, BorderLayout.CENTER);  // Parte central
        add(contactInputPanel, BorderLayout.SOUTH);  // Parte inferior

        // Actualiza la información de la cantidad de contactos en pantalla
        ContactInfoPanel.actualizarInfo();

        // Hacer la ventana visible
        setVisible(true); // Se agrega para asegurar que la ventana sea visible
    }

}
