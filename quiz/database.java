package com.quiz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class database {
    static HashMap<String, String> check = new HashMap<>();

    static String url = "jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:6543/postgres";

    static String user = "postgres.hjdtmuezrvdkjejshoge";

    static String password = "M@nthanvidhi";

    static PreparedStatement pst = null;

    static Connection con = null;

    static CallableStatement callable = null;

    static void getConnection() throws SQLException {
        con = DriverManager.getConnection(url, user, password);

        if (con != null) {
            System.out.println("Connection Successfully");
        } else {
            System.out.println("Connection Failed");
        }
    }

    static void prepareStatement(String query) throws Exception {
        pst = con.prepareStatement(query);
    }

    static void callableStatement(String query) throws Exception {
        callable = con.prepareCall(query);
    }

    public static void main(String[] args) throws Exception {

    }

    public static void checkUser() {

    }

}