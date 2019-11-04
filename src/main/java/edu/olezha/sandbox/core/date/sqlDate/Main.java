package edu.olezha.sandbox.core.date.sqlDate;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {

    private static final String CREATE_TABLE_SQL =
            "create table if not exists date_of (id int primary key auto_increment, date_of date, time_of time, timestamp_of timestamp)";
    private static final String WRITE_DATE_SQL =
            "insert into date_of (date_of, time_of, timestamp_of) values(?, ?, ?)";
    private static final String SELECT_ALL = "select * from date_of";

    public static void main(String... args) throws Exception {
        Class.forName("org.h2.Driver");

        Main main = new Main();
        main.createTable();
        main.writeDate(new java.util.Date());
        main.printAll();
    }

    private void createTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
        }
    }

    private void writeDate(java.util.Date date) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(WRITE_DATE_SQL)) {
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setTime(2, new java.sql.Time(date.getTime()));
            statement.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
            statement.executeUpdate();
        }
    }

    private void printAll() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next())
                log.info("{} | {} | {} | {}",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./data");
    }
}
