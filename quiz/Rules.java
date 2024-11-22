package com.quiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Rules extends JFrame implements ActionListener {

    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JButton backButton;

    String rulesText = "                            Rules\n\n"
            + "1. Quiz Format: \n"
            + "   - Answer all 7 compulsory questions in each quiz (Sports, History, Science).\n\n"
            + "2. Timing:\n"
            + "   - Each question has a time limit (15 second).\n\n"
            + "3. Scoring:\n"
            + "   - Earn points for correct answers.  No negative marking.\n\n"
            + "4. Completion:\n"
            + "   - Finish all questions to submit. Answers are final.\n\n"
            + "5. Ranking:\n"
            + "   - Your score is recorded in the Ranking section.";

    public Rules() {
        initButton();
        initTextArea();
        initPanel();
        initFrame();
    }

    void initFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 425);
        this.setLocation(350, 200);

        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);

        this.add(panel);
    }

    void initPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(155, 161, 157));
        panel.setBounds(0, 0, 360, 390);
        panel.setLayout(null);

        panel.add(backButton);
        panel.add(scrollPane);
    }

    void initTextArea() {
        textArea = new JTextArea(rulesText);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        // textArea.setForeground(Color.white);
        textArea.setBounds(0, 0, 340, 390);
        textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        // textArea.setBackground(new Color(33, 41, 48));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 340, 390);
    }

    void initButton() {
        backButton = new JButton("<");
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.black);
        backButton.setBounds(0, 0, 50, 25);
        backButton.setFocusable(true);
        backButton.addActionListener(this);
        backButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
        }
    }
}
