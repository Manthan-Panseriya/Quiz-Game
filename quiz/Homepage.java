package com.quiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Homepage extends JFrame implements ActionListener {

    static String quizType;

    JButton goBack = new JButton("Go Back");
    JButton ranking = new JButton("Ranking");
    JButton rules = new JButton();

    JButton sportsButton;
    JButton gkButton;
    JButton scienceButton;

    JLabel scienceQuiz;
    JLabel mathsQuiz;
    JLabel gkQuiz;
    JLabel bgImage;

    ImageIcon i1 = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\s1.jpg");

    ImageIcon i2 = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\his.png");

    ImageIcon i3 = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\sci.jpg");

    public Homepage() {

        initLabelForImages();
        initButtonForSelectedQuiz();

        // add button bounds
        goBack.setText("Sign Out");
        goBack.setBounds(30, 140, 100, 35);
        goBack.setBackground(new Color(168, 195, 188));
        goBack.setForeground(Color.black);
        goBack.setBorder(null);
        goBack.setFocusable(false);
        goBack.setFont(new Font("Sen Serif", Font.PLAIN, 17));
        goBack.addActionListener(this);

        ranking.setText("Ranking");
        ranking.setBounds(190, 140, 100, 35);
        ranking.setBackground(new Color(168, 195, 188));
        ranking.setForeground(Color.black);
        ranking.setBorder(null);
        ranking.setFocusable(false);
        ranking.setFont(new Font("Sen Serif", Font.PLAIN, 17));
        ranking.setOpaque(true);
        ranking.addActionListener(this);

        rules.setText("Rules");
        rules.setBounds(350, 140, 100, 35);
        rules.setBackground(new Color(168, 195, 188));
        rules.setForeground(Color.black);
        rules.setBorder(null);
        rules.setFocusable(false);
        rules.setFont(new Font("Sen Serif", Font.PLAIN, 17));
        rules.setOpaque(true);
        rules.addActionListener(this);

        JLabel northLabel = new JLabel("Quiz Game");
        northLabel.setBounds(500, 0, 1200, 75);
        northLabel.setBackground(new Color(168, 195, 188));
        northLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        northLabel.setForeground(Color.black);
        northLabel.setOpaque(true);

        JLabel centerJLabel = new JLabel();
        centerJLabel.setBounds(0, 75, 1200, 100);
        // centerJLabel.setBackground(Color.decode("#FEFBEA"));
        centerJLabel.setBackground(new Color(168, 195, 188));
        centerJLabel.setOpaque(true);

        JLabel lowerJLabel = new JLabel();
        lowerJLabel.setBounds(0, 175, 1200, (1200 - 175));
        lowerJLabel.setBackground(Color.decode("#FEFBEA"));
        lowerJLabel.setOpaque(true);

        JLabel buttonLabel = new JLabel();
        buttonLabel.setBounds(0, 75, 1200, 100);
        buttonLabel.add(rules);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBounds(0, 0, 1200, 1200);
        panel.setBackground(new Color(168, 195, 188));
        panel.add(goBack);
        panel.add(ranking);
        panel.add(rules);
        panel.add(scienceQuiz);
        panel.add(mathsQuiz);
        panel.add(gkQuiz);
        panel.add(sportsButton);
        panel.add(gkButton);
        panel.add(scienceButton);
        // panel.add(bgImage);
        panel.add(northLabel);
        panel.add(centerJLabel);
        panel.add(lowerJLabel);

        this.setTitle("Quiz Game");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(1200, 1200);
        this.setVisible(true);

        this.setContentPane(panel);

    }
    // this.add(panel, BorderLayout.CENTER);
    // this.add(bgImage);

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sportsButton) {
            this.dispose();
            quizType = "Sports Quiz";
            new SportsQuiz();
        } else if (e.getSource() == gkButton) {
            this.dispose();
            quizType = "History Quiz";
            new HistoryQuiz();
        } else if (e.getSource() == scienceButton) {
            this.dispose();
            quizType = "Science Quiz";
            new ScienceQuiz();
        }

        if (e.getSource() == goBack) {
            new L1();
            this.dispose();
        } else if (e.getSource() == ranking) {
            new Ranking();
        } else if (e.getSource() == rules) {
            new Rules();
        }
    }

    void initLabelForImages() {

        scienceQuiz = new JLabel(i1);
        scienceQuiz.setBounds(100, 225, 250, 250);

        mathsQuiz = new JLabel(i2);
        mathsQuiz.setBounds(450, 225, 250, 250);

        gkQuiz = new JLabel(i3);
        gkQuiz.setBounds(800, 225, 250, 250);

    }

    void initButtonForSelectedQuiz() {
        sportsButton = new JButton("Start");
        sportsButton.setBounds(175, 500, 100, 35);
        sportsButton.setBackground(Color.black);
        sportsButton.setForeground(Color.white);
        sportsButton.setFocusable(false);
        sportsButton.setFont(new Font("Consalas", Font.PLAIN, 17));
        sportsButton.addActionListener(this);

        gkButton = new JButton("Start");
        gkButton.setBounds(525, 500, 100, 35);
        gkButton.setBackground(Color.black);
        gkButton.setForeground(Color.white);
        gkButton.setFocusable(false);
        gkButton.setFont(new Font("Consalas", Font.PLAIN, 17));
        gkButton.addActionListener(this);

        scienceButton = new JButton("Start");
        scienceButton.setBounds(875, 500, 100, 35);
        scienceButton.setBackground(Color.black);
        scienceButton.setForeground(Color.white);
        scienceButton.setFocusable(false);
        scienceButton.setFont(new Font("Consalas", Font.PLAIN, 17));
        scienceButton.addActionListener(this);

    }

    protected static String getQuizName() {
        return quizType;
    }

    public static void main(String[] args) {
        new Homepage();
    }

}
