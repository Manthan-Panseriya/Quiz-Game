package com.quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class QuizResult extends JFrame implements ActionListener {

    private JPanel panel;
    private JTable resultTable;
    private JScrollPane scrollPane;

    private JLabel quizLabel;
    private JLabel line;
    private JLabel username;
    private JLabel correctAns;
    private JLabel wrongAns;
    private JLabel quizType;

    private String user = User.getUser();
    private String quizName = Homepage.getQuizName();

    private JButton homepage;
    private JButton exit;

    protected static String[] queString;
    protected static String ans[];

    private StringBuffer sb = new StringBuffer("");
    private int que = 0;
    private int an = 0;

    public QuizResult() {
        initTable();
        initPanel();
        initFrame();
        populateTable();

    }

    void initFrame() {
        this.setTitle("Quiz Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        this.setVisible(true);

        this.add(panel);
    }

    void initPanel() {

        initLabel();
        initButton();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBounds(0, 0, 1200, 1200);
        panel.setBackground(new Color(123, 50, 250));

        panel.add(scrollPane);
        panel.add(username);
        panel.add(correctAns);
        panel.add(wrongAns);
        panel.add(quizType);
        panel.add(homepage);
        panel.add(exit);
        panel.add(quizLabel);
        panel.add(line);
    }

    void initLabel() {

        quizLabel = new JLabel("Quiz Result");
        quizLabel.setForeground(Color.white);
        quizLabel.setFont(new Font("Consolas", Font.PLAIN, 40));
        quizLabel.setOpaque(false);
        quizLabel.setBounds(100, 100, 400, 100);

        line = new JLabel("_____________");
        line.setForeground(Color.white);
        line.setFont(new Font("Consolas", Font.PLAIN, 40));
        line.setOpaque(false);
        line.setBounds(100, 120, 400, 100);

        username = new JLabel("Username : " + user);
        username.setForeground(Color.white);
        username.setFont(new Font("Consolas", Font.PLAIN, 25));
        username.setOpaque(false);
        username.setBounds(100, 170, 400, 100);

        correctAns = new JLabel("Correct Answer : " + User.correctAns);
        correctAns.setForeground(Color.white);
        correctAns.setFont(new Font("Consolas", Font.PLAIN, 25));
        correctAns.setOpaque(false);
        correctAns.setBounds(100, 220, 400, 100);

        wrongAns = new JLabel("Wrong Answer : " + User.wrongAns);
        wrongAns.setForeground(Color.white);
        wrongAns.setFont(new Font("Consolas", Font.PLAIN, 25));
        wrongAns.setOpaque(false);
        wrongAns.setBounds(100, 270, 400, 100);

        quizType = new JLabel("Quiz Type : " + quizName);
        quizType.setForeground(Color.white);
        quizType.setFont(new Font("Consolas", Font.PLAIN, 25));
        quizType.setOpaque(false);
        quizType.setBounds(100, 320, 400, 100);

        // resultSummary = new JLabel(getResultSummary());
        // resultSummary.setForeground(Color.white);
        // resultSummary.setFont(new Font("Consolas", Font.PLAIN, 20));
        // resultSummary.setOpaque(false);
        // resultSummary.setBounds(100, 370, 600, 300);

    }

    void initButton() {
        homepage = new JButton("Homepage");
        homepage.setBounds(100, 460, 100, 30);
        homepage.setFocusable(false);
        homepage.addActionListener(this);

        exit = new JButton("EXIT");
        exit.setBounds(220, 460, 100, 30);
        exit.setFocusable(false);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homepage) {
            new Homepage();
            this.dispose();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    protected static void insertInDatabase(String query) {
        try {

            database.callableStatement(query);
            database.callable.setInt(1, User.userid);
            database.callable.setString(2, User.curUser);
            database.callable.setString(3, Homepage.getQuizName());
            database.callable.setInt(4, User.correctAns);
            database.callable.setInt(5, User.wrongAns);
            database.callable.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void populateTable() {
        // Get the table model
        DefaultTableModel tableModel = (DefaultTableModel) resultTable.getModel();

        // Clear existing data
        tableModel.setRowCount(0);

        // Add rows to the table
        if (queString != null && ans != null) {
            for (int i = 0; i < queString.length; i++) {
                String questionText = queString[i];
                String answerText = ans[i];
                String result = "Wrong"; // Default to "No"

                // Determine if the answer is correct
                if ("correct".equalsIgnoreCase(answerText)) {
                    result = "Correct";
                }

                // Add row to the table model
                tableModel.addRow(new Object[] { questionText, result });
            }
        }
    }

    void initTable() {
        // Define columns
        String[] columnNames = { "Question", "Your Answer" };

        // Create a DefaultTableModel with column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Create JTable with the model
        resultTable = new JTable(tableModel);
        resultTable.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());

        resultTable.setRowHeight(30);

        // Create a JScrollPane and add the JTable to it
        scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(450, 180, 630, 210); // Adjust bounds as needed
    }

    public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        public WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText(value.toString());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            setFont(table.getFont());

            // Adjust the preferred size based on the text
            int rowHeight = getPreferredSize().height;
            table.setRowHeight(row, rowHeight + 10); // Add padding for better visibility

            return this;
        }
    }

}
