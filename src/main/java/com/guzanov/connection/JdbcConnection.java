package com.guzanov.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class JdbcConnection {
    // TODO: 26.04.2022 logger here
    private static Connection CONNECTION;
    private static final String URL = "jdbc:postgresql://localhost:5432/aikamsoft_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        if (Objects.isNull(CONNECTION)) {
            try {
                Class.forName("org.postgresql.Driver");
                CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                // TODO: 27.04.2022 LOG
                e.printStackTrace();
            }
        }
        return CONNECTION;
    }
}
