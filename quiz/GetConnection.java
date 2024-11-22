package com.quiz;

import java.sql.SQLException;

/**
 * GetConnection
 */
public interface GetConnection {

    static void getConnection() {
        try {
            database.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}