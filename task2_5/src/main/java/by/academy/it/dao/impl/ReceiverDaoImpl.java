package by.academy.it.dao.impl;

import by.academy.it.connection.ConnectionManager;
import by.academy.it.dao.ReceiverDao;
import by.academy.it.exception.DaoException;
import by.academy.it.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiverDaoImpl implements ReceiverDao {

    private static ReceiverDaoImpl instance;
    private final ConnectionManager connectionManager;

    private static final String SAVE_SQL = "insert into receiver (r_name) values (?) ";
    private static final String FIND_ALL_SQL = "select num, r_name from receiver";
    private static final String NUM_COLUMN = "num";
    private static final String NAME_COLUMN = "r_name";

    private ReceiverDaoImpl() {
        connectionManager = ConnectionManager.take();
    }

    public static ReceiverDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiverDaoImpl();
        }
        return instance;
    }

    @Override
    public Receiver save(Receiver receiver) {
        try (Connection connection = connectionManager.open();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, receiver.getName());
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                receiver = receiver.updateWithNum(generatedKeys.getInt(1));
            }
            return receiver;
        } catch (SQLException e) {
            throw new DaoException("Receiver saving unsuccessful", e);
        }
    }

    @Override
    public List<Receiver> findAll() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);
            List<Receiver> receivers = new ArrayList<>();
            while (resultSet.next()) {
                Receiver receiver = mapResultSet(resultSet);
                receivers.add(receiver);
            }
            return receivers;
        } catch (SQLException e) {
            throw new DaoException("FindAll method failed", e);
        }
    }

    private Receiver mapResultSet(ResultSet resultSet) throws SQLException {
        return new Receiver(
                resultSet.getInt(NUM_COLUMN),
                resultSet.getString(NAME_COLUMN));
    }
}
