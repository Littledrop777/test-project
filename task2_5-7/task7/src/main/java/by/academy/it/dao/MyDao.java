package by.academy.it.dao;

import by.academy.it.connection.ConnectionManager;
import by.academy.it.exception.DaoException;
import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyDao implements Dao{

    private static MyDao instance;
    private final ConnectionManager connectionManager;

    private static final String SAVE_RECEIVER_SQL = "insert into receiver (r_name) values (?)";
    private static final String SAVE_EXPENSE_SQL = "insert into expense (pay_date, receiver_num, pay_value) values (?, ?, ?)";
    private static final String FIND_RECEIVER_BY_NUM_SQL = "select num, r_name from receiver where num = ?";
    private static final String FIND_EXPENSE_BY_NUM_SQL =
            "SELECT ex.num, pay_date, rec.num, rec.r_name, pay_value\n" +
                    "from expense ex\n" +
                    "         JOIN receiver rec on rec.num = ex.receiver_num\n" +
                    "where ex.num = ?";
    private static final String FIND_ALL_RECEIVERS_SQL = "select num, r_name from receiver";
    private static final String FIND_ALL_EXPENSES_SQL =
            "SELECT ex.num, ex.pay_date, rec.num, rec.r_name, ex.pay_value from expense ex " +
                    "JOIN receiver rec ON rec.num = ex.receiver_num";
    private static final String FIND_RECEIVER_BY_NAME_SQL = "select num, r_name from receiver where r_name = ? ";

    private static final String NUM_COLUMN = "num";
    private static final String RECEIVER_NAME_COLUMN = "r_name";
    private static final String DATE_COLUMN = "pay_date";
    private static final String VALUE_COLUMN = "pay_value";

    private MyDao() {
        connectionManager = ConnectionManager.take();
    }

    public static MyDao getInstance() {
        if (instance == null) {
            instance = new MyDao();
        }
        return instance;
    }

    @Override
    public Optional<Receiver> getReceiver(int num) {
        try (Connection connection = connectionManager.open();
             final PreparedStatement statement = connection.prepareStatement(FIND_RECEIVER_BY_NUM_SQL)) {
            statement.setInt(1, num);
            ResultSet resultSet = statement.executeQuery();
            Receiver receiver = null;
            if (resultSet.next()) {
                receiver = receiverMapResultSet(resultSet);
            }
            return Optional.ofNullable(receiver);
        } catch (SQLException e) {
            throw new DaoException("getReceiver method failed", e);
        }
    }

    @Override
    public List<Receiver> getReceivers() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_RECEIVERS_SQL);
            List<Receiver> receivers = new ArrayList<>();
            while (resultSet.next()) {
                Receiver receiver = receiverMapResultSet(resultSet);
                receivers.add(receiver);
            }
            return receivers;
        } catch (SQLException e) {
            throw new DaoException("getReceivers method failed", e);
        }
    }

    @Override
    public Optional<Expense> getExpense(int num) {
        try (Connection connection = connectionManager.open();
             final PreparedStatement statement = connection.prepareStatement(FIND_EXPENSE_BY_NUM_SQL)) {
            statement.setInt(1, num);
            ResultSet resultSet = statement.executeQuery();
            Expense expense = null;
            if (resultSet.next()) {
                expense = expenseMapResultSet(resultSet);
            }
            return Optional.ofNullable(expense);
        } catch (SQLException e) {
            throw new DaoException("getExpense method failed", e);
        }
    }

    @Override
    public List<Expense> getExpenses() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EXPENSES_SQL);
            List<Expense> expenses = new ArrayList<>();
            while (resultSet.next()) {
                Expense expense = expenseMapResultSet(resultSet);
                expenses.add(expense);
            }
            return expenses;
        } catch (SQLException e) {
            throw new DaoException("getExpenses method failed", e);
        }
    }

    @Override
    public Receiver addReceiver(Receiver receiver) {
        try (Connection connection = connectionManager.open();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_RECEIVER_SQL, Statement.RETURN_GENERATED_KEYS)) {
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
    public Expense addExpense(Expense expense) {
        try (Connection connection = connectionManager.open();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_EXPENSE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setDate(1, Date.valueOf(expense.getDate()));
            prepareStatement.setInt(2, expense.getReceiver().getNum());
            prepareStatement.setDouble(3, expense.getValue());
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                expense = expense.updateWithNum(generatedKeys.getInt(1));
            }
            return expense;
        } catch (SQLException e) {
            throw new DaoException("Expense saving unsuccessful", e);
        }
    }

    @Override
    public Optional<Receiver> getReceiverByName(String name) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_RECEIVER_BY_NAME_SQL)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Receiver receiver = null;
            if (resultSet.next()) {
                receiver = receiverMapResultSet(resultSet);
            }
            return Optional.ofNullable(receiver);
        } catch (SQLException e) {
            throw new DaoException("getReceiverByName method failed", e);
        }
    }

    private Receiver receiverMapResultSet(ResultSet resultSet) throws SQLException {
        return new Receiver(
                resultSet.getInt(NUM_COLUMN),
                resultSet.getString(RECEIVER_NAME_COLUMN));
    }

    private Expense expenseMapResultSet(ResultSet resultSet) throws SQLException {
        Receiver receiver = receiverMapResultSet(resultSet);
        return new Expense(
                resultSet.getInt(NUM_COLUMN),
                resultSet.getDate(DATE_COLUMN).toLocalDate(),
                receiver,
                resultSet.getDouble(VALUE_COLUMN));
    }
}
