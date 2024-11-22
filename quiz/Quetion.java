package com.quiz;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Quetion {
    protected String question;
    protected String option_a;
    protected String option_b;
    protected String option_c;
    protected String option_d;
    protected String correct_option;

    // protected static ArrayList<Quetion> sports = new ArrayList<>();
    // protected static ArrayList<Quetion> history = new ArrayList<>();
    // protected static ArrayList<Quetion> science = new ArrayList<>();

    // Quetion() {
    // }

    public Quetion(String question, String option_a, String option_b, String option_c, String option_d,
            String correct_option) {
        this.question = question;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct_option = correct_option;
    }

    // static ArrayList<Quetion> getSportsQuetion() {
    // String query = "select quetion,optiona,optionb,optionc,optiond,correctans
    // from sportsqna order by RANDOM() Limit 7";
    // try {
    // database.getConnection();
    // database.prepareStatement(query);

    // ResultSet rs = database.pst.executeQuery();
    // while (rs.next()) {
    // String question = rs.getString(1);
    // String optiona = rs.getString(2);
    // String optionb = rs.getString(3);
    // String optionc = rs.getString(4);
    // String optiond = rs.getString(5);
    // String ans = rs.getString(6);

    // sports.add(new Quetion(question, optiona, optionb, optionc, optiond, ans));

    // }
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return sports;
    // }

    // static ArrayList<Quetion> getHistoryQuetion() {
    // String query = "select quetion,optiona,optionb,optionc,optiond,correctans
    // from historyqna";
    // try {
    // database.getConnection();
    // database.prepareStatement(query);

    // ResultSet rs = database.pst.executeQuery();
    // while (rs.next()) {
    // String question = rs.getString(1);
    // String optiona = rs.getString(2);
    // String optionb = rs.getString(3);
    // String optionc = rs.getString(4);
    // String optiond = rs.getString(5);
    // String ans = rs.getString(6);

    // history.add(new Quetion(question, optiona, optionb, optionc, optiond, ans));

    // }
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return history;
    // }

    // static ArrayList<Quetion> getScienceQuetion() {
    // String query = "select quetion,optiona,optionb,optionc,optiond,correctans
    // from scienceqna";
    // try {
    // database.getConnection();
    // database.prepareStatement(query);

    // ResultSet rs = database.pst.executeQuery();
    // while (rs.next()) {
    // String question = rs.getString(1);
    // String optiona = rs.getString(2);
    // String optionb = rs.getString(3);
    // String optionc = rs.getString(4);
    // String optiond = rs.getString(5);
    // String ans = rs.getString(6);

    // science.add(new Quetion(question, optiona, optionb, optionc, optiond, ans));

    // }
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return science;
    // }

    String getOption1() {
        return this.option_a;
    }

    String getOption2() {
        return this.option_b;
    }

    String getOption3() {
        return this.option_c;
    }

    String getOption4() {
        return this.option_d;
    }

    String getAns() {
        return this.correct_option;
    }

    String getQuetion() {
        return this.question;
    }

}
