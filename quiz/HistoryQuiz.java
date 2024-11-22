package com.quiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class HistoryQuiz extends JFrame implements ActionListener {
    // For User Details
    private String curUser = User.getUser();
    private int correctAnsCount = 0;
    private int wrongAnsCount = 0;
    private String correctAns;
    private String selectedAns;
    private int currentQuestion = 0;

    // Optional to show which ans was correct or wrong
    int[] wrongAnsNo = {};
    int[] correctAnsNo = {};

    // Panel
    protected JPanel panel;

    // ImageIcon
    protected static ImageIcon im;
    protected static ImageIcon head;
    protected static ImageIcon userLogo;

    // ArrayList for questions
    // private ArrayList<Quetion> sport = L1.historyQuetions;
    Queue1 historyQueue;

    // Labels
    protected JLabel upperLabel;
    protected JLabel userInfo;
    protected JTextArea questionLabel;
    protected JLabel optionALabel;
    protected JLabel optionBLabel;
    protected JLabel optionCLabel;
    protected JLabel optionDLabel;
    protected JLabel userImage;
    protected JLabel timerLabel;
    protected JLabel forImage = new JLabel(im);

    // Option Buttons
    JRadioButton option1, option2, option3, option4;
    ButtonGroup options;

    // Buttons
    protected JButton nextButton;
    protected JButton finishButton;

    private Timer questionTimer;
    private int timeRemaining = 15;

    private int i = 0;
    // Initialize Images
    static {
        im = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\q1 (1).jpg");

        head = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\historyLogo-removebg-preview.png");
        Image h = head.getImage().getScaledInstance(225, 225, Image.SCALE_DEFAULT);
        head = new ImageIcon(h);

        userLogo = new ImageIcon("C:\\quiz_platform\\src\\main\\java\\com\\quiz\\userLogo1.png");
        Image i = userLogo.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        userLogo = new ImageIcon(i);
    }

    public HistoryQuiz() {
        QuizResult.queString = new String[7];
        QuizResult.ans = new String[7];

        historyQueue = q.getHistoryQuetion();
        setLabel();
        jButtons();
        initRadioButtons();
        initPanel();
        initFrame();
        startTimer();
        showQuetion();
    }

    void startTimer() {
        // Timer triggers every 1000ms (1 second)
        questionTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time left: " + timeRemaining + " seconds");

                if (timeRemaining <= 0) {
                    questionTimer.stop(); // Stop the timer
                    // nextButton.setEnabled(true);
                    timeUp(); // Force user to move to the next question
                }
            }
        });
        questionTimer.start(); // Start the countdown
    }

    void resetTimer() {
        options.clearSelection();
        timeRemaining = 15; // Reset time for next question
        questionTimer.restart(); // Restart the timer for the next question
    }

    void timeUp() {
        // Handle scenario when time is up (automatically go to next question)
        wrongAnsCount++;
        QuizResult.ans[i] = "wrong";
        i++;
        currentQuestion++;
        if (currentQuestion <= 6) {

            showQuetion();
        } else {
            User.correctAns = correctAnsCount;
            User.wrongAns = wrongAnsCount;
            new QuizResult();
        }
    }

    void initFrame() {
        this.setTitle("Quiz Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 1200);
        // this.setLayout(null);
        this.setVisible(true);
        this.add(panel);

    }

    void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBounds(0, 0, 1200, 1200);

        panel.add(userInfo);
        // panel.add(text);
        panel.add(timerLabel);
        panel.add(userImage);
        panel.add(upperLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(nextButton);
        panel.add(finishButton);
        panel.add(questionLabel);
        panel.add(forImage);

    }

    void setLabel() {

        timerLabel = new JLabel("Time left :" + timeRemaining + " seconds");
        timerLabel.setBounds(1050, 270, 300, 30);
        timerLabel.setForeground(Color.RED);

        userImage = new JLabel(userLogo);
        userImage.setBounds(10, 10, 50, 50);
        userImage.setOpaque(false);

        forImage.setBounds(0, -300, 1200, 1200);
        forImage.setVisible(true);

        questionLabel = new JTextArea();
        questionLabel.setForeground(Color.black);
        questionLabel.setBackground(new Color(35, 80, 165));
        questionLabel.setFont(new Font("Consalas", Font.PLAIN, 35));
        questionLabel.setOpaque(false);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setLineWrap(true);
        questionLabel.setEditable(false);
        questionLabel.setBounds(210, 280, 800, 100);

        userInfo = new JLabel("" + curUser);
        userInfo.setForeground(Color.WHITE);
        userInfo.setFont(new Font("Consalas", Font.PLAIN, 25));
        userInfo.setBounds(60, 10, 300, 50);
        userInfo.setOpaque(false);

        upperLabel = new JLabel(head);
        upperLabel.setBounds(490, 0, 225, 225);
        upperLabel.setOpaque(false);

    }

    void initRadioButtons() {
        option1 = new JRadioButton();
        option1.setBounds(210, 465, 400, 50);
        option1.setFocusable(false);
        option1.setForeground(Color.BLACK);
        option1.setFont(new Font("Consolas", Font.PLAIN, 25));
        option1.setIconTextGap(20);
        option1.setOpaque(false);

        option2 = new JRadioButton();
        option2.setBounds(690, 465, 400, 50);
        option2.setFocusable(false);
        option2.setForeground(Color.BLACK);
        option2.setFont(new Font("Consolas", Font.PLAIN, 25));
        option2.setIconTextGap(20);
        option2.setOpaque(false);

        option3 = new JRadioButton();
        option3.setBounds(210, 570, 400, 50);
        option3.setFocusable(false);
        option3.setForeground(Color.BLACK);
        option3.setFont(new Font("Consolas", Font.PLAIN, 25));
        option3.setIconTextGap(20);
        option3.setOpaque(false);

        option4 = new JRadioButton();
        option4.setBounds(690, 570, 400, 50);
        option4.setFocusable(false);
        option4.setForeground(Color.BLACK);
        option4.setFont(new Font("Consalas", Font.PLAIN, 25));
        option4.setIconTextGap(20);
        option4.setOpaque(false);

        options = new ButtonGroup();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);
        option4.addActionListener(this);
    }

    void jButtons() {
        nextButton = new JButton("Next");
        nextButton.setBounds(1062, 310, 100, 30);
        nextButton.setFocusable(false);
        nextButton.setOpaque(false);
        nextButton.addActionListener(this);
        nextButton.setEnabled(false);

        finishButton = new JButton("Finish");
        finishButton.setBounds(1062, 360, 100, 30);
        finishButton.setFocusable(false);
        finishButton.setOpaque(false);
        finishButton.addActionListener(this);
        finishButton.setEnabled(false);

    }

    void showQuetion() {
        if (currentQuestion >= 6) {
            nextButton.setEnabled(false);
            // finishButton.setEnabled(true);
            questionTimer.stop();
        }
        resetTimer();
        // Quetion que = sport.get(currentQuestion);
        Quetion que = historyQueue.dequeue();
        QuizResult.queString[i] = que.getQuetion();
        questionLabel.setText(que.getQuetion());
        option1.setText(que.getOption1());
        option2.setText(que.getOption2());
        option3.setText(que.getOption3());
        option4.setText(que.getOption4());
        correctAns = que.getAns();
        // if (currentQuestion == 6) {
        // finishButton.setEnabled(true);
        // nextButton.setEnabled(false);
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == option1 && currentQuestion < 6) {
            selectedAns = "optiona";
            nextButton.setEnabled(true);
        } else if (e.getSource() == option2 && currentQuestion < 6) {
            selectedAns = "optionb";
            nextButton.setEnabled(true);
        } else if (e.getSource() == option3 && currentQuestion < 6) {
            selectedAns = "optionc";
            nextButton.setEnabled(true);
        } else if (e.getSource() == option4 && currentQuestion < 6) {
            selectedAns = "optiond";
            nextButton.setEnabled(true);
        } else {
            if (e.getSource() == option1) {
                selectedAns = "optiona";
                finishButton.setEnabled(true);
            } else if (e.getSource() == option2) {
                selectedAns = "optionb";
                finishButton.setEnabled(true);
            } else if (e.getSource() == option3) {
                selectedAns = "optionc";
                finishButton.setEnabled(true);
            } else if (e.getSource() == option4) {
                selectedAns = "optiond";
                finishButton.setEnabled(true);
            }
        }

        if (e.getSource() == nextButton) {
            options.clearSelection();
            if (selectedAns.equals(correctAns)) {
                QuizResult.ans[i] = "correct";
                i++;
                correctAnsCount++;
            } else {
                QuizResult.ans[i] = "wrong";
                i++;
                wrongAnsCount++;
            }

            currentQuestion++;
            showQuetion();
            nextButton.setEnabled(false);
        } else if (e.getSource() == finishButton) {
            questionTimer.stop();
            if (selectedAns.equals(correctAns)) {
                QuizResult.ans[i] = "correct";
                i++;
                correctAnsCount++;
            } else {
                QuizResult.ans[i] = "wrong";
                i++;
                wrongAnsCount++;
            }
            User.correctAns = correctAnsCount;
            User.wrongAns = wrongAnsCount;
            this.dispose();
            new QuizResult();
            User.insertData();
        }
    }

}
