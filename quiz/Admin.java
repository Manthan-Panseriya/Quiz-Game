package com.quiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.quiz.Linkedlist.Node;

public class Admin extends JFrame implements ActionListener, GetConnection {
    private JPanel panel;

    private JLabel text;

    private JButton seeUser;
    private JButton sportsquiz;
    private JButton historyquiz;
    private JButton sciencequiz;
    private JButton addquetion;
    private JButton signOut;

    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;

    private int XPOINT = 400;

    public Admin() {
        GetConnection.getConnection();
        initButton();
        initLabel();
        initPanel();
        initFrame();
        addMouseListenerToFrame();
    }

    private void initFrame() {
        this.setTitle("Quiz Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        // this.setLayout(null);
        this.setVisible(true);
        this.add(panel);
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1200, 1200);
        panel.setBackground(new Color(96, 116, 161));

        panel.add(signOut);
        panel.add(addquetion);
        panel.add(sciencequiz);
        panel.add(historyquiz);
        panel.add(sportsquiz);
        panel.add(seeUser);
        panel.add(text);
    }

    private void initLabel() {
        text = new JLabel("Hey Admin !!");
        text.setBounds(100, 100, 300, 100);
        text.setForeground(Color.white);
        text.setOpaque(false);
        text.setFont(new Font("Bangers", Font.PLAIN, 25));

    }

    public static void main(String[] args) {
        new Admin();
    }

    void initButton() {
        seeUser = new JButton("View Users");
        seeUser.setBounds(100, 200, 150, 30);
        seeUser.setFocusable(false);
        seeUser.addActionListener(this);

        sportsquiz = new JButton("Sports Quiz Rank");
        sportsquiz.setBounds(300, 200, 150, 30);
        sportsquiz.setFocusable(false);
        sportsquiz.addActionListener(this);

        historyquiz = new JButton("History Quiz Rank");
        historyquiz.setBounds(500, 200, 150, 30);
        historyquiz.setFocusable(false);
        historyquiz.addActionListener(this);

        sciencequiz = new JButton("Science Quiz Rank");
        sciencequiz.setBounds(700, 200, 150, 30);
        sciencequiz.setFocusable(false);
        sciencequiz.addActionListener(this);

        addquetion = new JButton("Add Question");
        addquetion.setBounds(900, 200, 150, 30);
        addquetion.setFocusable(false);
        addquetion.addActionListener(this);

        signOut = new JButton("Sign Out");
        signOut.setBounds(30, 30, 150, 30);
        signOut.setFocusable(false);
        signOut.setBackground(new Color(96, 116, 161));
        signOut.addActionListener(this);
        signOut.setForeground(Color.white);
        signOut.setFocusable(false);
        signOut.setOpaque(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (scrollPane != null) {
            panel.remove(scrollPane);
            scrollPane = null; // Reset scrollPane to null
            panel.revalidate(); // Refresh the panel to reflect changes
            panel.repaint(); // Repaint the panel
        }
        if (e.getSource() == seeUser) {
            String query = "Select userid ,username from userdata";

            try {
                // database.getConnection();
                database.prepareStatement(query);

                ResultSet rs = database.pst.executeQuery();
                Linkedlist.first = null;
                while (rs.next()) {
                    Userd temp = new Userd();
                    temp.userid = rs.getInt(1);
                    temp.name = rs.getString(2);

                    new Linkedlist().addLast(temp);
                }

            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }
            tableModel = new DefaultTableModel();
            table = new JTable(tableModel);

            tableModel.addColumn("userid");
            tableModel.addColumn("username");

            Node temp = Linkedlist.first;
            while (temp != null) {
                tableModel.addRow(new Object[] { temp.obj.userid, temp.obj.name });
                temp = temp.next;
            }
            table.setRowHeight(30);

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(100, 275, 300, 300);

            header = table.getTableHeader();
            header.setFont(new Font("Arial", Font.PLAIN, 16));

            table.setFont(new Font("Arial", Font.PLAIN, 16));
            int rowHeight = table.getRowHeight();
            int rowCount = table.getRowCount();
            int preferredHeight = rowHeight * rowCount + table.getTableHeader().getPreferredSize().height;
            scrollPane.setPreferredSize(new Dimension(300, preferredHeight));

            panel.add(scrollPane);
        } else if (e.getSource() == sportsquiz) {
            String s = "Sports Quiz";
            getTable(s);
        } else if (e.getSource() == historyquiz) {
            String s = "History Quiz";
            getTable(s);
        } else if (e.getSource() == sciencequiz) {
            String s = "Science Quiz";
            getTable(s);
        } else if (e.getSource() == addquetion) {
            showAddQuestionForm();
        } else if (e.getSource() == signOut) {
            this.dispose();
            new L1();
        }

    }

    void getTable(String s) {
        String query = "Select resultid,userid ,username,correctans,incorrectans from quizresult where quiztype = '" + s
                + "'";

        try {
            database.prepareStatement(query);

            ResultSet rs = database.pst.executeQuery();
            Linkedlist.first = null;
            while (rs.next()) {
                Userd temp = new Userd();
                temp.resultid = rs.getInt(1);
                temp.userid = rs.getInt(2);
                temp.name = rs.getString(3);
                temp.score = rs.getInt(4);
                temp.wrongans = rs.getInt(5);

                new Linkedlist().orderedInsert(temp);
            }

        } catch (Exception exc) {
            System.out.println(exc.getLocalizedMessage());
        }
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        tableModel.addColumn("result id");
        tableModel.addColumn("user id");
        tableModel.addColumn("Username");
        tableModel.addColumn("Score");
        tableModel.addColumn("Wrong ans");

        Node temp = Linkedlist.first;
        while (temp != null) {
            tableModel.addRow(new Object[] { temp.obj.resultid, temp.obj.userid, temp.obj.name, temp.obj.score,
                    temp.obj.wrongans });
            temp = temp.next;
        }
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(XPOINT, 275, 500, 300);

        header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 16));

        table.setFont(new Font("Arial", Font.PLAIN, 16));

        int rowHeight = table.getRowHeight();
        int rowCount = table.getRowCount();
        int preferredHeight = rowHeight * rowCount + table.getTableHeader().getPreferredSize().height;
        scrollPane.setPreferredSize(new Dimension(500, preferredHeight));

        panel.add(scrollPane);
    }

    private void addMouseListenerToFrame() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (scrollPane != null && !scrollPane.getBounds().contains(e.getPoint())) {
                    panel.remove(scrollPane);
                    scrollPane = null;
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });
    }

    private void showAddQuestionForm() {
        JFrame questionFrame = new JFrame("Add Question");
        questionFrame.setSize(400, 350);
        questionFrame.setLocation(657, 275);
        questionFrame.setUndecorated(true);
        questionFrame.setLayout(null);
        questionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Quiz Type
        JLabel quizTypeLabel = new JLabel("Quiz Type:");
        quizTypeLabel.setBounds(10, 10, 100, 25);
        JComboBox<String> quizTypeCombo = new JComboBox<>(
                new String[] { "Sports Quiz", "History Quiz", "Science Quiz" });
        quizTypeCombo.setBounds(120, 10, 250, 25);

        // Question
        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setBounds(10, 50, 100, 25);
        JTextField questionField = new JTextField();
        questionField.setBounds(120, 50, 250, 25);

        // Option A
        JLabel optionALabel = new JLabel("Option A:");
        optionALabel.setBounds(10, 90, 100, 25);
        JTextField optionAField = new JTextField();
        optionAField.setBounds(120, 90, 250, 25);

        // Option B
        JLabel optionBLabel = new JLabel("Option B:");
        optionBLabel.setBounds(10, 130, 100, 25);
        JTextField optionBField = new JTextField();
        optionBField.setBounds(120, 130, 250, 25);

        // Option C
        JLabel optionCLabel = new JLabel("Option C:");
        optionCLabel.setBounds(10, 170, 100, 25);
        JTextField optionCField = new JTextField();
        optionCField.setBounds(120, 170, 250, 25);

        // Option D
        JLabel optionDLabel = new JLabel("Option D:");
        optionDLabel.setBounds(10, 210, 100, 25);
        JTextField optionDField = new JTextField();
        optionDField.setBounds(120, 210, 250, 25);

        // Correct Option
        JLabel correctOptionLabel = new JLabel("Correct Option:");
        correctOptionLabel.setBounds(10, 250, 100, 25);
        JComboBox<String> correctOptionCombo = new JComboBox<>(
                new String[] { "optiona", "optionb", "optionc", "optiond" });
        correctOptionCombo.setBounds(120, 250, 100, 25);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 290, 120, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quizType = getQuizType((String) quizTypeCombo.getSelectedItem());
                String question = questionField.getText();
                String optionA = optionAField.getText();
                String optionB = optionBField.getText();
                String optionC = optionCField.getText();
                String optionD = optionDField.getText();
                String correctOption = (String) correctOptionCombo.getSelectedItem();

                // Validate input
                if (quizType == null || question.isEmpty() || optionA.isEmpty() || optionB.isEmpty()
                        || optionC.isEmpty() || optionD.isEmpty() || correctOption == null) {
                    JOptionPane.showMessageDialog(questionFrame, "Please fill in all fields.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // database.getConnection();

                        // Insert question into the database
                        // String query = "INSERT INTO " + quizType
                        // + "(quetion, optiona, optionb, optionc, optiond, correctans) VALUES (?, ?, ?,
                        // ?, ?, ?)";
                        // database.prepareStatement(query);
                        String query = "{Call addquestion(?,?,?,?,?,?,?)}";
                        database.callableStatement(query);
                        database.callable.setString(1, quizType);
                        database.callable.setString(2, question);
                        database.callable.setString(3, optionA);
                        database.callable.setString(4, optionB);
                        database.callable.setString(5, optionC);
                        database.callable.setString(6, optionD);
                        database.callable.setString(7, correctOption);
                        database.callable.executeUpdate();
                        JOptionPane.showMessageDialog(questionFrame, "Question added successfully.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        questionFrame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(questionFrame, "An error occurred while adding the question.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add components to the frame
        questionFrame.add(quizTypeLabel);
        questionFrame.add(quizTypeCombo);
        questionFrame.add(questionLabel);
        questionFrame.add(questionField);
        questionFrame.add(optionALabel);
        questionFrame.add(optionAField);
        questionFrame.add(optionBLabel);
        questionFrame.add(optionBField);
        questionFrame.add(optionCLabel);
        questionFrame.add(optionCField);
        questionFrame.add(optionDLabel);
        questionFrame.add(optionDField);
        questionFrame.add(correctOptionLabel);
        questionFrame.add(correctOptionCombo);
        questionFrame.add(submitButton);

        questionFrame.setVisible(true);
    }

    private String getQuizType(String type) {
        if (type.equals("Sports Quiz")) {
            return "sportsqna";
        } else if (type.equals("History Quiz")) {
            return "historyqna";
        } else if (type.equals("Science Quiz")) {
            return "scienceqna";
        }
        return null;
    }
}
