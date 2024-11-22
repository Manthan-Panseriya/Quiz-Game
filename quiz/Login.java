package com.quiz;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;

public class Login implements ActionListener {
    static JButton button;
    static JFrame frame;
    static JTextField username;
    static JPasswordField password;
    static HashMap<String, String> user = new HashMap<>();

    public static void main(String[] args) {
        button = new JButton("Login");
        button.setBounds(800, 425, 100, 50);
        button.setFocusable(false);
        button.addActionListener(new Login());

        ImageIcon icon = new ImageIcon("Quiz3-removebg-preview.png");

        username = new JTextField();
        // username.setPreferredSize(new Dimension());
        username.setBounds(730, 205, 250, 40);

        password = new JPasswordField();
        password.setBounds(730, 320, 250, 40);

        JLabel uLabel = new JLabel("Username");
        uLabel.setBounds(730, 139, 100, 100);
        uLabel.setFont(new Font("Consalas", Font.PLAIN, 15));

        JLabel uPassword = new JLabel("Password");
        uPassword.setBounds(730, 255, 100, 100);
        uPassword.setFont(new Font("Consalas", Font.PLAIN, 15));

        JLabel image = new JLabel();
        // image.setIcon(icon);
        image.setBounds(0, 0, 500, 1000);
        image.setBackground(new Color(168, 195, 188));
        image.setOpaque(true);

        JLabel i = new JLabel();
        i.setIcon(icon);
        i.setBackground(new Color(168, 195, 188));
        i.setBounds(0, 200, 200, 200);
        i.setOpaque(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        // panel.setBackground(Color.white);
        // panel.setPreferredSize(new Dimension(500, 500));
        panel.setBounds(350, 350, 500, 500);
        panel.add(uLabel);
        panel.add(uPassword);
        panel.add(button);
        panel.add(image);
        panel.add(username);
        panel.add(password);

        frame = new JFrame("Quiz Game");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        frame.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String user = username.getText();
            String pass = password.getSelectedText();
            try {
                // checkUser();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

}
