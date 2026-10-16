package com.ragelite.ui;

import com.ragelite.db.UserDAO;
import com.ragelite.model.Admin;
import com.ragelite.model.Researcher;
import com.ragelite.model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {

    public RegisterDialog(JFrame parent, UserDAO userDAO) {
        super(parent, "Register New User", true);
        setSize(350, 260);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"ADMIN", "RESEARCHER"});
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Role:"), gbc);
        gbc.gridx = 1; add(roleBox, gbc);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(saveButton, gbc);

        gbc.gridy = 5;
        add(statusLabel, gbc);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());
                String role = (String) roleBox.getSelectedItem();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    statusLabel.setText("All fields required.");
                    return;
                }

                User newUser = "ADMIN".equals(role)
                        ? new Admin(0, name, email, password)
                        : new Researcher(0, name, email, password);

                userDAO.add(newUser);
                statusLabel.setForeground(new Color(0, 128, 0));
                statusLabel.setText("Registered! You can login now.");
            } catch (Exception ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });
    }
}