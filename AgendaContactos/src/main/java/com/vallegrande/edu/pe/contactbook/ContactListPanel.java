package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase que representa el panel donde se muestra la lista de contactos
public class ContactListPanel extends JPanel {
    // Modelo de lista que almacena los contactos
    private static DefaultListModel<Contact> contactListModel;
    // Lista visual que muestra los contactos
    private JList<Contact> contactList;
    // Botones para editar y eliminar contactos
    private JButton btnEditContact;
    private JButton btnDeleteContact;
    // Etiqueta para mostrar el total de contactos
    private static JLabel lblTotalContacts;

    // Constructor del panel
    public ContactListPanel() {
        // Establece el color de fondo del panel
        setBackground(new Color(255, 251, 202));
        // Usa un layout que organiza los componentes en regiones (norte, sur, centro, etc.)
        setLayout(new BorderLayout());

        // Inicializa el modelo de lista y la lista de contactos
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        // Permite seleccionar solo un contacto a la vez
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Inicialización de los botones
        btnEditContact = new JButton("Editar");
        btnDeleteContact = new JButton("Eliminar");

        // Acción del botón "Editar"
        btnEditContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex(); // Índice del contacto seleccionado
                if (selectedIndex != -1) {
                    Contact contact = contactListModel.get(selectedIndex); // Obtiene el contacto seleccionado
                    // Solicita un nuevo nombre al usuario
                    String newName = JOptionPane.showInputDialog(null,
                            "Edite el nombre del contacto",
                            contact.getName());
                    // Si se ingresa un nuevo nombre válido, se actualiza el contacto
                    if (newName != null && !newName.isEmpty()) {
                        contact.setName(newName);
                        contactListModel.setElementAt(contact, selectedIndex);
                    }
                }
            }
        });

        // Acción del botón "Eliminar"
        btnDeleteContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex(); // Índice del contacto seleccionado
                // Si hay un contacto seleccionado, se elimina del modelo
                if (selectedIndex != -1) {
                    contactListModel.removeElementAt(selectedIndex);
                    // Actualizamos el contador después de eliminar un contacto
                    updateContactCount();
                }
            }
        });

        // Panel que contiene los botones de acción
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnEditContact);
        buttonPanel.add(btnDeleteContact);

        // Panel superior con la etiqueta "Total de contactos"
        JPanel topPanel = new JPanel();
        lblTotalContacts = new JLabel("Total de contactos: 0");
        lblTotalContacts.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(lblTotalContacts);

        // Agrega el panel superior en la parte superior
        add(topPanel, BorderLayout.NORTH);

        // Agrega la lista de contactos al centro del panel con barra de desplazamiento
        add(new JScrollPane(contactList), BorderLayout.CENTER);
        // Elimina el panel de botones de la parte inferior para no mostrar "Total de contactos"
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método estático que permite añadir un contacto a la lista
    public static void addContact(Contact contact) {
        contactListModel.addElement(contact);
        // Actualizar el contador de contactos
        updateContactCount();
    }

    // Método para actualizar el contador de contactos
    private static void updateContactCount() {
        int contactCount = contactListModel.size();
        lblTotalContacts.setText("Total de contactos: " + contactCount);
    }

    // Método estático que devuelve el número total de contactos
    public static int getContactCount() {
        return contactListModel.size();
    }
}
