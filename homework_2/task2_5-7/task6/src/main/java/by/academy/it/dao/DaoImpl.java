package by.academy.it.dao;

import by.academy.it.connection.ConnectionManager;
import by.academy.it.dto.ExpenseDto;
import by.academy.it.dto.ReceiverDto;
import by.academy.it.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoImpl implements Dao{

    private static final String FIND_ALL_WITH_SUM_PAYMENT_SQL =
            "SELECT r_name, (SELECT sum(pay_value) FROM expense WHERE receiver_num = rec.num) AS sum\n" +
                    "FROM receiver rec";
    private static final String FIND_ALL_PAYMENT_BY_DATE_WITH_MAX_PAY_SQL =
            "SELECT pay_date, sum(pay_value) AS sum\n" +
                    "FROM expense\n" +
                    "WHERE pay_value = (SELECT max(pay_value) FROM expense)\n" +
                    "GROUP BY pay_date";
    private static final String FIND_MAX_PAYMENT_BY_DAY_WITH_MAX_SUM_SQL =
            "SELECT pay_date, max(pay_value) as max_pay\n" +
                    "FROM expense\n" +
                    "WHERE pay_date = (SELECT pay_date\n" +
                    "                  FROM (SELECT pay_date, sum(pay_value) as sum\n" +
                    "                        FROM expense\n" +
                    "                        GROUP BY pay_date) sum_value\n" +
                    "                  WHERE sum_value.sum = (SELECT max(sum)\n" +
                    "                                         FROM (SELECT sum(pay_value) AS sum\n" +
                    "                                               FROM expense\n" +
                    "                                               GROUP BY pay_date) sum_v))\n" +
                    "GROUP BY pay_date";
    public static final String PAY_DATE_COLUMN = "pay_date";
    public static final String RECEIVER_NAME_COLUMN = "r_name";
    public static final String SUM_COLUMN = "sum";
    public static final String MAX_PAY_COLUMN = "max_pay";

    private static DaoImpl instance;
    private final ConnectionManager connectionManager;

    private DaoImpl() {
        connectionManager = ConnectionManager.take();
    }

    public static DaoImpl getInstance() {
        if (instance == null) {
            instance = new DaoImpl();
        }
        return instance;
    }

    @Override
    public List<ReceiverDto> findAllWithSumPay() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_WITH_SUM_PAYMENT_SQL);
            List<ReceiverDto> receivers = new ArrayList<>();
            while (resultSet.next()) {
                ReceiverDto receiver = new ReceiverDto(
                        resultSet.getString(RECEIVER_NAME_COLUMN),
                        resultSet.getDouble(SUM_COLUMN));
                receivers.add(receiver);
            }
            return receivers;
        } catch (SQLException e) {
            throw new DaoException("FindAllWithSumPay method failed", e);
        }
    }

    @Override
    public List<ExpenseDto> findAllPaymentByDateWithMaxPay() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_PAYMENT_BY_DATE_WITH_MAX_PAY_SQL);
            List<ExpenseDto> expenses = new ArrayList<>();
            while (resultSet.next()) {
                ExpenseDto expense = new ExpenseDto(
                        resultSet.getDate(PAY_DATE_COLUMN).toLocalDate(),
                        resultSet.getDouble(SUM_COLUMN));
                expenses.add(expense);
            }
            return expenses;
        } catch (SQLException e) {
            throw new DaoException("FindAllPaymentByDateWithMaxPay method failed", e);
        }
    }

    @Override
    public Optional<ExpenseDto> findMaxPaymentByDayWithMaxSum() {
        try (Connection connection = connectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_MAX_PAYMENT_BY_DAY_WITH_MAX_SUM_SQL);
            ExpenseDto expense = null;
            if (resultSet.next()) {
                expense = new ExpenseDto(
                        resultSet.getDate(PAY_DATE_COLUMN).toLocalDate(),
                        resultSet.getDouble(MAX_PAY_COLUMN));
            }
            return Optional.ofNullable(expense);
        } catch (SQLException e) {
            throw new DaoException("FindAllPaymentByDateWithMaxPay method failed", e);
        }
    }
}
