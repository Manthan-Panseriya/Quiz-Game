package com.quiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

// import javax.swing.SwingUtilities;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// class QuizGame {
//     private static final String[] QUESTIONS = {
//             "What is the capital of France?",
//             "What is 2 + 2?"
//     };

//     private static final String[][] ANSWERS = {
//             { "Paris", "London", "Berlin", "Madrid" },
//             { "3", "4", "5", "6" }
//     };

//     private static final int[] CORRECT_ANSWERS = { 0, 1 }; // Indexes of correct answers

//     private static int currentQuestion = 0;

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(QuizGame::createAndShowGUI);
//     }

//     private static void createAndShowGUI() {
//         JFrame frame = new JFrame("Quiz Game");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(500, 400);

//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         JLabel questionLabel = new JLabel(QUESTIONS[currentQuestion]);
//         panel.add(questionLabel);

//         ButtonGroup answerGroup = new ButtonGroup();
//         JRadioButton[] answerButtons = new JRadioButton[ANSWERS[currentQuestion].length];
//         for (int i = 0; i < ANSWERS[currentQuestion].length; i++) {
//             answerButtons[i] = new JRadioButton(ANSWERS[currentQuestion][i]);
//             answerGroup.add(answerButtons[i]);
//             panel.add(answerButtons[i]);
//         }

//         JButton submitButton = new JButton("Submit");
//         panel.add(submitButton);

//         JLabel feedbackLabel = new JLabel("");
//         panel.add(feedbackLabel);

//         submitButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 int selectedIndex = -1;
//                 for (int i = 0; i < answerButtons.length; i++) {
//                     if (answerButtons[i].isSelected()) {
//                         selectedIndex = i;
//                         break;
//                     }
//                 }

//                 if (selectedIndex == CORRECT_ANSWERS[currentQuestion]) {
//                     feedbackLabel.setText("Correct!");
//                 } else {
//                     feedbackLabel.setText("Incorrect. Try again.");
//                 }

//                 currentQuestion++;
//                 if (currentQuestion < QUESTIONS.length) {
//                     questionLabel.setText(QUESTIONS[currentQuestion]);
//                     for (int i = 0; i < answerButtons.length; i++) {
//                         answerButtons[i].setText(ANSWERS[currentQuestion][i]);
//                     }
//                 } else {
//                     questionLabel.setText("Quiz completed!");
//                     for (JRadioButton button : answerButtons) {
//                         button.setEnabled(false);
//                     }
//                     submitButton.setEnabled(false);
//                 }
//             }
//         });

//         frame.getContentPane().add(panel);
//         frame.setVisible(true);
//     }
// }

public class x {
    public static void main(String[] args) {
        // Queue1 sportsQueue = q.getSportsQuetion();
        // Quetion question = sportsQueue.dequeue();
        // if (question != null) {
        // System.out.println(question.getQuetion());
        // }
        new y();

    }
}

// package com.quiz;

// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.awt.Image;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

// import javax.swing.ButtonGroup;
// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JRadioButton;
// import javax.swing.JTextArea;
// import javax.swing.Timer;

class y extends JFrame implements ActionListener {
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
    Queue1 history = q.getSportsQuetion();

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
    protected JButton lifeline;

    private Timer questionTimer;
    private int timeRemaining = 15;

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

    public y() {
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
        wrongAnsCount++; // Mark the question wrong if time is up
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
        panel.add(lifeline);
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
        finishButton.setBounds(1062, 410, 100, 30);
        finishButton.setFocusable(false);
        finishButton.setOpaque(false);
        finishButton.addActionListener(this);
        finishButton.setEnabled(false);

        lifeline = new JButton("50-50");
        lifeline.setBounds(1062, 360, 100, 30);
        lifeline.setFocusable(false);
        lifeline.setOpaque(false);
        lifeline.addActionListener(this);

    }

    void showQuetion() {
        if (currentQuestion >= 6) {
            nextButton.setEnabled(false);
            // finishButton.setEnabled(true);
            questionTimer.stop();
        }
        resetTimer();
        Quetion que = history.dequeue();
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
                correctAnsCount++;
            } else {
                wrongAnsCount++;
            }

            currentQuestion++;
            showQuetion();
            nextButton.setEnabled(false);
        } else if (e.getSource() == lifeline) {

        } else if (e.getSource() == finishButton) {
            questionTimer.stop();
            if (selectedAns.equals(correctAns)) {
                correctAnsCount++;
            } else {
                wrongAnsCount++;
            }
            User.correctAns = correctAnsCount;
            User.wrongAns = wrongAnsCount;
            new QuizResult();
            User.insertData();
        }
    }

}
