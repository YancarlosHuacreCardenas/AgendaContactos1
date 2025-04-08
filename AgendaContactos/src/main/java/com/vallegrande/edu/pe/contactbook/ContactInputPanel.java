package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactInputPanel extends JPanel {

    private JTextField txtName, txtPhoneNumber, txtEmail;
    private JButton btnAddContact;
    private JComboBox<String> countryCodeComboBox;

    // Constructor
    public ContactInputPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());

        // Establecer colores
        Color labelColor = new Color(50, 50, 50);
        Color textFieldBackground = new Color(255, 255, 255);
        Color buttonColor = new Color(100, 150, 255);

        // Establecer tipografía
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblName = new JLabel("Nombre: ");
        JLabel lblPhoneNumber = new JLabel("Teléfono: ");
        JLabel lblEmail = new JLabel("Correo electrónico: ");
        JLabel lblCountryCode = new JLabel("Código de país: ");

        // Estilizar los JLabel
        lblName.setFont(labelFont);
        lblPhoneNumber.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblCountryCode.setFont(labelFont);

        txtName = new JTextField(25);
        txtPhoneNumber = new JTextField(25);
        txtEmail = new JTextField(25);

        // Estilizar los JTextField
        txtName.setFont(textFieldFont);
        txtPhoneNumber.setFont(textFieldFont);
        txtEmail.setFont(textFieldFont);
        txtName.setBackground(textFieldBackground);
        txtPhoneNumber.setBackground(textFieldBackground);
        txtEmail.setBackground(textFieldBackground);

        // ComboBox para seleccionar el código de país
        String[] countryCodes = {"+51", "+1", "+34", "+44", "+57"};
        countryCodeComboBox = new JComboBox<>(countryCodes);
        countryCodeComboBox.setFont(textFieldFont);

        btnAddContact = new JButton("Agregar Contacto");
        btnAddContact.setPreferredSize(new Dimension(150, 35));
        btnAddContact.setFont(new Font("Arial", Font.BOLD, 16));
        btnAddContact.setBackground(buttonColor);
        btnAddContact.setForeground(Color.WHITE);
        btnAddContact.setFocusPainted(false);

        // Acción al presionar el botón
        btnAddContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String phoneNumber = txtPhoneNumber.getText();
                String email = txtEmail.getText();

                if (!isValidName(name)) {
                    JOptionPane.showMessageDialog(null,
                            "El nombre solo puede contener letras.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(null,
                            "El teléfono solo puede contener números y debe ser válido.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null,
                            "El correo electrónico debe ser válido (terminar en @vallegrande.edu.pe o @gmail.com).",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Si pasa todas las validaciones, agrega el contacto
                ContactListPanel.addContact(new Contact(name, phoneNumber, email));
                txtName.setText("");
                txtPhoneNumber.setText("");
                txtEmail.setText("");
            }
        });

        // Posicionamiento con GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Agregar los componentes al panel con el layout GridBag
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPhoneNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPhoneNumber, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(countryCodeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnAddContact, gbc);

        // Limitación en la entrada del nombre para permitir solo letras
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // Solo permitir letras, cancelar el evento si no es una letra
                if (!Character.isLetter(evt.getKeyChar())) {
                    evt.consume();  // No permite la entrada
                }
            }
        });

        // Limitación en la entrada del teléfono para permitir solo números
        txtPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                String countryCode = (String) countryCodeComboBox.getSelectedItem();
                // Definir la cantidad máxima de números dependiendo del código de país
                int maxLength = getMaxPhoneLength(countryCode);

                // Verificar si el número de teléfono ya tiene la longitud máxima permitida
                if (txtPhoneNumber.getText().length() >= maxLength) {
                    evt.consume(); // No permitir más caracteres
                }

                // Permitir solo números
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });

        // Cuando el código de país cambia, ajustamos la longitud del número de teléfono permitido
        countryCodeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryCode = (String) countryCodeComboBox.getSelectedItem();
                // Actualizar la longitud máxima permitida según el código de país
                int maxLength = getMaxPhoneLength(countryCode);
                // Ajustar la longitud máxima del teléfono
                txtPhoneNumber.setText(txtPhoneNumber.getText().substring(0, Math.min(txtPhoneNumber.getText().length(), maxLength)));
            }
        });
    }

    // Método para obtener la longitud máxima del número de teléfono según el código de país
    private int getMaxPhoneLength(String countryCode) {
        switch (countryCode) {
            case "+51": // Perú
                return 9;  // 9 dígitos
            case "+1":  // EE. UU. / Canadá
                return 10; // 10 dígitos
            case "+34": // España
                return 9;  // 9 dígitos
            case "+44": // Reino Unido
                return 10; // 10 dígitos
            case "+57": // Colombia
                return 10; // 10 dígitos
            default:
                return 10; // Por defecto, se asume 10 dígitos
        }
    }

    // Método para validar el nombre
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");  // Solo letras
    }

    // Método para validar el teléfono
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d+");  // Solo números
    }

    // Método para validar el correo electrónico
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@(vallegrande\\.edu\\.pe|gmail\\.com)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
