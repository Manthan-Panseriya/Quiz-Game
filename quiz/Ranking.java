package com.quiz;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Ranking extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel top1;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private JButton button;

    void initButtton() {
        button = new JButton("<");
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBounds(0, 0, 50, 29);
        button.setFocusable(false);
        button.addActionListener(this);

    }

    public Ranking() {

        initButtton();
        init();
        table();
        getRanking();
        initPanel();
        initFrame();
    }

    void initFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(220, 170);
        this.setLocation(200, 210);
        this.setLayout(null);
        this.setUndecorated(true);

        this.add(panel);
        this.setVisible(true);

    }

    void initPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(155, 161, 157));
        panel.setBounds(0, 0, 200, 150);
        panel.setLayout(null);

        while (Linkedlist.first != null) {
            tableModel.addRow(new Object[] { Linkedlist.first.obj.name, Linkedlist.first.obj.score });
            Linkedlist.first = Linkedlist.first.next;
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 30, 190, 200);

        header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 16));

        table.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(button);
        panel.add(label);
        panel.add(scrollPane);
    }

    public void table() {
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        tableModel.addColumn("Username");
        tableModel.addColumn("Score");
    }

    void getRanking() {
        Linkedlist.first = null;
        try {
            // database.getConnection();
            String query = "select username,sum(correctans) from quizresult group by username order by sum(correctans)";
            database.prepareStatement(query);
            ResultSet rs = database.pst.executeQuery();

            while (rs.next()) {
                Userd x = new Userd();
                x.name = rs.getString(1);
                x.score = rs.getInt(2);

                new Linkedlist().orderedInsert(x);
                // tableModel.addRow(new Object[] { name, score });
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private JLabel label;

    void init() {
        label = new JLabel("Overall Ranking");
        label.setBounds(50, 5, 100, 15);
        label.setForeground(Color.BLACK);
        label.setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
        }
    }

}
