package by.academy.it.dao.impl;

import by.academy.it.connection.ConnectionManager;
import by.academy.it.dao.ExpenseDao;
import by.academy.it.exception.DaoException;
import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {

    private static ExpenseDaoImpl instance;
    private final ConnectionManager connectionManager;

    private static final String SAVE_SQL = "insert into expense (pay_date, receiver_num, pay_value) values (?, ?, ?)";
    private static final String FIND_ALL_SQL =
            "SELECT ex.num, ex.pay_date, receiver_num, rec.r_name, ex.pay_value from expense ex " +
                    "JOIN receiver rec ON rec.num = ex.receiver_num";
    private static final String NUM_COLUMN = "num";
    private static final String DATE_COLUMN = "pay_date";
    private static final String VALUE_COLUMN = "pay_value";

    private ExpenseDaoImpl() {
        connectionManager = ConnectionManager.take();
    }

    public static ExpenseDaoImpl getInstance() {
        if (instance == null) {
            instance = new ExpenseDaoImpl();
        }
        return instance;
    }

    @Override
    public Expense save(Expense expense) {
        try (Connection connection = connectionManager.open(); 
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
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
    public List<Expense> findAll() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);
            List<Expense> expenses = new ArrayList<>();
            while (resultSet.next()) {
                Expense expense = mapResultSet(resultSet);
                expenses.add(expense);
            }
            return expenses;
        } catch (SQLException e) {
            throw new DaoException("FindAll method failed", e);
        }
    }

    private Expense mapResultSet(ResultSet resultSet) throws SQLException {
        Receiver receiver = new Receiver(
                resultSet.getInt("num"),
                resultSet.getString("r_name"));
        return new Expense(
                resultSet.getInt(NUM_COLUMN),
                resultSet.getDate(DATE_COLUMN).toLocalDate(),
                receiver,
                resultSet.getDouble(VALUE_COLUMN));
    }
}
