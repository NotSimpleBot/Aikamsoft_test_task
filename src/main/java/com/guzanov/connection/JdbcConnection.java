package com.guzanov.connection;

import com.guzanov.helpers.ErrorInJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Класс для коннекта к базе данных PostgreSQL
 */
public class JdbcConnection {
    private static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/aikamsoft_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    /**
     * Статический фабричный метод - возвращает поднятое соединение до БД.
     *
     * @return Соединение до базы данных на основе константных полей класса JdbcConnection
     */
    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                ErrorInJson.writeError(e);
            }
        }
        return connection;
    }
}
