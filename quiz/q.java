package com.quiz;

import java.sql.ResultSet;

// public class q {

// }
public class q {
    protected String question;
    protected String option_a;
    protected String option_b;
    protected String option_c;
    protected String option_d;
    protected String correct_option;

    protected static Queue1 sportsQueue = new Queue1();
    protected static Queue1 historyQueue = new Queue1();
    protected static Queue1 scienceQueue = new Queue1();

    public q(String question, String option_a, String option_b, String option_c, String option_d,
            String correct_option) {
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct_option = correct_option;
    }

    static Queue1 getSportsQuetion() {
        sportsQueue.setSize(7); // Set the size externally
        sportsQueue.front = -1;
        sportsQueue.rear = -1;

        String query = "select quetion,optiona,optionb,optionc,optiond,correctans from sportsqna order by RANDOM() Limit 7";
        try {
            // database.getConnection();
            database.prepareStatement(query);

            ResultSet rs = database.pst.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                String optiona = rs.getString(2);
                String optionb = rs.getString(3);
                String optionc = rs.getString(4);
                String optiond = rs.getString(5);
                String ans = rs.getString(6);

                sportsQueue.enqueue(new Quetion(question, optiona, optionb, optionc, optiond, ans));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sportsQueue;
    }

    static Queue1 getHistoryQuetion() {
        historyQueue.setSize(7); // Set the size externally
        historyQueue.front = -1;
        historyQueue.rear = -1;

        String query = "select quetion,optiona,optionb,optionc,optiond,correctans from historyqna order by RANDOM() Limit 7";
        try {
            database.prepareStatement(query);

            ResultSet rs = database.pst.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                String optiona = rs.getString(2);
                String optionb = rs.getString(3);
                String optionc = rs.getString(4);
                String optiond = rs.getString(5);
                String ans = rs.getString(6);

                historyQueue.enqueue(new Quetion(question, optiona, optionb, optionc, optiond, ans));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return historyQueue;
    }

    static Queue1 getScienceQuetion() {
        scienceQueue.setSize(7); // Set the size externally
        scienceQueue.front = -1;
        scienceQueue.rear = -1;

        String query = "select quetion,optiona,optionb,optionc,optiond,correctans from scienceqna order by RANDOM() Limit 7";
        try {
            // database.getConnection();
            database.prepareStatement(query);

            ResultSet rs = database.pst.executeQuery();
            while (rs.next()) {
                String question = rs.getString(1);
                String optiona = rs.getString(2);
                String optionb = rs.getString(3);
                String optionc = rs.getString(4);
                String optiond = rs.getString(5);
                String ans = rs.getString(6);

                scienceQueue.enqueue(new Quetion(question, optiona, optionb, optionc, optiond, ans));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return scienceQueue;
    }
}
