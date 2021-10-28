package by.academy.it.connection;

import java.sql.Connection;

public interface ConnectionManager {

    Connection open();

    static ConnectionManager take() {
        return DataSource.getInstance();
    }
}
