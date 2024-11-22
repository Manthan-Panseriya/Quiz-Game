package com.quiz;

import java.sql.ResultSet;

public class User {
    public static String curUser;
    public static int correctAns;
    public static int wrongAns;
    public static int userid;

    public User() {
    }

    public User(String user) {
        curUser = user;
        getUserid(user);
    }

    protected static String getUser() {
        return curUser;
    }

    protected static int getCorrectAnsCount() {
        return correctAns;
    }

    protected static int getWrongAnsCount() {
        return wrongAns;
    }

    static void insertData() {

        // String query = "insert into quizresult
        // (userid,username,quiztype,correctans,incorrectans) values (" + userid
        // + ",'" + curUser + "','"
        // + Homepage.getQuizName() + "'," + correctAns + "," + wrongAns + ")";
        String query = "{Call addtoresult(?,?,?,?,?)}";
        QuizResult.insertInDatabase(query);
    }

    private void getUserid(String user) {
        String sql = "Select userid from userdata where username = ?";
        try {
            // database.getConnection();
            database.prepareStatement(sql);
            database.pst.setString(1, user);
            ResultSet rs = database.pst.executeQuery();
            while (rs.next()) {
                userid = rs.getInt(1);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
