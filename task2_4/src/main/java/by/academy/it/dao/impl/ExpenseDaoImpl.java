package by.academy.it.dao.impl;

import by.academy.it.connection.ConnectionManager;
import by.academy.it.dao.ExpenseDao;
import by.academy.it.dao.ReceiverDao;
import by.academy.it.exception.DaoException;
import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseDaoImpl implements ExpenseDao {

    private static ExpenseDaoImpl instance;
    private final ConnectionManager connectionManager;

    private static final String SAVE_SQL = "insert into expense (pay_date, receiver_num, pay_value) values ('%s', %d, %f)";
    private static final String FIND_ALL_SQL =
            "SELECT ex.num, ex.pay_date, receiver_num, rec.r_name, ex.pay_value from expense ex " +
                    "JOIN receiver rec ON rec.num = ex.receiver_num";
    private static final String UPDATE_SQL = "update expense set pay_date = %s, receiver_num = %d, pay_value = %f where num = %d";
    private static final String DELETE_SQL = "delete from expense where num = %d ";
    private static final String FIND_BY_NUM_SQL = "SELECT ex.num, pay_date, rec.num, rec.r_name, pay_value from expense ex" +
            "JOIN receiver rec ON rec.num = ex.receiver_num where ex.num = %d";

    private static final String NUM_COLUMN = "num";
    private static final String DATE_COLUMN = "pay_date";
    private static final String RECEIVER_NUM_COLUMN = "receiver_num";
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
             Statement statement = connection.createStatement()) {
            String sql = String.format(SAVE_SQL, Date.valueOf(expense.getDate()), expense.getReceiver().getNum(), expense.getValue());
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
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

    @Override
    public Optional<Expense> findByNum(int num) {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            String sql = String.format(FIND_BY_NUM_SQL, num);
            ResultSet resultSet = statement.executeQuery(sql);
            Expense expense = null;
            if (resultSet.next()) {
                expense = mapResultSet(resultSet);
            }
            return Optional.ofNullable(expense);
        } catch (SQLException e) {
            throw new DaoException("FindAll method failed", e);
        }
    }

    @Override
    public void update(Expense expense) {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            String sql = String.format(UPDATE_SQL,
                    Date.valueOf(expense.getDate()),
                    expense.getReceiver().getNum(),
                    expense.getValue(),
                    expense.getNum());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException("Update entity unsuccessful", e);
        }
    }

    @Override
    public void delete(int num) {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            String sql = String.format(DELETE_SQL, num);
            statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new DaoException("Entity deletion failed", e);
        }
    }

    private Expense mapResultSet(ResultSet resultSet) throws SQLException {
        Receiver receiver = new Receiver(
                resultSet.getInt("num"),
                resultSet.getString("r_name"));
        final Optional<Receiver> byNum = ReceiverDao.retrieve().findByNum(resultSet.getInt(RECEIVER_NUM_COLUMN));
        return new Expense(
                resultSet.getInt(NUM_COLUMN),
                resultSet.getDate(DATE_COLUMN).toLocalDate(),
                receiver,
                resultSet.getDouble(VALUE_COLUMN));
    }
}
