package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Clase que representa un panel informativo en la interfaz, mostrando datos sobre los contactos
public class ContactInfoPanel extends JPanel {
    // Etiqueta estática para mostrar información (por ejemplo, cantidad de contactos)
    private static JLabel lblInfo;

    // Constructor del panel, donde se configura el diseño y se inicializa la etiqueta
    public ContactInfoPanel() {
        // Establece el layout del panel como BorderLayout
        setLayout(new BorderLayout());

        // Inicializa la etiqueta con un mensaje predeterminado
        lblInfo = new JLabel("Gestiona tus contactos fácilmente");
        // Agrega un borde con espacio en la parte superior e inferior
        lblInfo.setBorder(new EmptyBorder(15, 0, 15, 0));
        // Define la fuente de la etiqueta
        lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        // Centra horizontalmente el texto de la etiqueta
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // Añade la etiqueta al centro del panel
        add(lblInfo, BorderLayout.CENTER);
    }

    // Método estático que actualiza el texto de la etiqueta
    public static void actualizarInfo() {
        // Actualiza el texto de la etiqueta sin mostrar el total de contactos
        lblInfo.setText("Gestiona tus contactos fácilmente");
    }
}
