package by.academy.it.connection;

import by.academy.it.exception.CouldNotInitializeConnectionManagerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements ConnectionManager {

    private static DataSource instance;

    private static final String URL = "jdbc:mysql://localhost:3306/accounting";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private DataSource() {
        loadDriver();
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    @Override
    public Connection open() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new CouldNotInitializeConnectionManagerException("failed open connection", e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new CouldNotInitializeConnectionManagerException("driver loading failed", e);
        }
    }
}
