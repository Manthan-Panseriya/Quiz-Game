package com.quiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class L1 extends JFrame implements ActionListener {
    // static ArrayList<Quetion> sportsQuetions = Quetion.getSportsQuetion();
    // static ArrayList<Quetion> historyQuetions = Quetion.getHistoryQuetion();
    // static ArrayList<Quetion> scienceQuetions = Quetion.getScienceQuetion();

    // static Queue1 sportsQueue = q.getSportsQuetion();
    // static Queue1 historyQueue = q.getSportsQuetion();
    // static Queue1 scienceQueue = q.getSportsQuetion();

    static HashMap<String, String> userPass = new HashMap<>();
    JButton button;
    JButton signIn;
    JFrame frame;
    JTextField username;
    JTextField password;
    // JLabel validity1 = new JLabel("Invalid Username or Password");
    // JLabel validity2 = new JLabel("Invalid Username");
    ImageIcon icon = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\Quiz3-removebg-preview.png");

    JLabel w = new JLabel();

    L1() {

        try {
            fetchAllUserData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // SignUp BUtton
        button = new JButton("Sign Up");
        button.setBounds(880, 425, 100, 35);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setBackground(Color.black);
        button.setForeground(Color.white);

        // SignIn button
        signIn = new JButton("Sign In");
        signIn.setBounds(730, 425, 100, 35);
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        signIn.setBackground(Color.black);
        signIn.setForeground(Color.white);

        // for Username details
        username = new JTextField();
        username.setPreferredSize(new Dimension(40, 10));
        username.setBounds(730, 205, 250, 40);
        username.setMargin(new Insets(0, 4, 0, 4));

        password = new JTextField();
        password.setBounds(730, 320, 250, 40);

        JLabel uLabel = new JLabel("Username");
        uLabel.setBounds(730, 139, 100, 100);
        uLabel.setFont(new Font("Consalas", Font.PLAIN, 15));

        JLabel uPassword = new JLabel("Password");
        uPassword.setBounds(730, 255, 100, 100);
        uPassword.setFont(new Font("Consalas", Font.PLAIN, 15));

        JLabel image = new JLabel();
        // image.setIcon(icon);
        image.setBounds(0, 0, 570, 1000);
        image.setBackground(new Color(168, 195, 188));
        image.setOpaque(true);

        // Add image
        JLabel io = new JLabel(icon);
        io.setBounds(0, 75, 580, 430);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        // panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBounds(350, 350, 500, 500);
        panel.add(w);
        panel.add(uLabel);
        panel.add(uPassword);
        panel.add(button);
        panel.add(signIn);
        panel.add(io);
        panel.add(image);
        panel.add(username);
        panel.add(password);

        // frame
        this.setTitle("Quiz Game");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setContentPane(panel);
        // this.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = getUsername().trim();
        String pass = getPassword().trim();

        if (e.getSource() == signIn) {

            w.setText("Invalid Password or Username");
            w.setForeground(Color.black);
            w.setBounds(730, 370, 500, 20);
            System.out.println(userPass);
            if (userPass.containsKey(username) && userPass.get(username).equals(pass)) {
                this.dispose();
                new User(username);
                if (username.equals("admin") && pass.equals("admin")) {
                    new Admin();
                } else {
                    new Homepage();
                }

            } else {
                w.setOpaque(true);
                // warning.setText("Invalid Password or Username");
            }

        }

        if (e.getSource() == button) {
            w.setText("User Already Exist");
            w.setForeground(Color.black);
            w.setBounds(730, 370, 500, 20);
            if (userPass.containsKey(username)) {
                w.setOpaque(true);

            } else {
                try {
                    // String sql = "insert into userdata (username,password) values (?,?)";
                    String sql = "{Call addUser (?,?)}";
                    database.callableStatement(sql);

                    database.callable.setString(1, username);
                    database.callable.setString(2, pass);
                    database.callable.executeUpdate();
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }
                userPass.put(username, pass);
                new User(username);
                this.dispose();
                new Homepage();

            }

        }

    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();

    }

    public void fetchAllUserData() throws Exception {
        String query = "select username,password from userdata";
        if (database.con == null) {
            database.getConnection();
        }
        database.prepareStatement(query);

        ResultSet rs = database.pst.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            String pass = rs.getString("password");

            userPass.put(username, pass);
        }

    }
}
