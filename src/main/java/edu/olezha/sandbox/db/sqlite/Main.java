package edu.olezha.sandbox.db.sqlite;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {

    private static final java.lang.String CREATE_TEST_TABLE_SQL =
            "create table if not exists test (id integer primary key autoincrement, name text)";
    private static final String WRITE_NAME_SQL =
            "insert into test (name) values(?)";
    private static final String SELECT_ALL = "select * from test";

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.createTable();
        main.writeName("Oleh");
        main.printAll();
    }

    private void createTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TEST_TABLE_SQL);
        }
    }

    private void writeName(String name) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(WRITE_NAME_SQL)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    private void printAll() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next())
                log.info("{} {}", resultSet.getString(1), resultSet.getString(2));
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:./sqlite.db");
    }
}
