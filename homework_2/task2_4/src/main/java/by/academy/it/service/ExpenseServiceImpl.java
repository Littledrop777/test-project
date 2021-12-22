package by.academy.it.service;

import by.academy.it.dao.ExpenseDao;
import by.academy.it.exception.UnknownEntityException;
import by.academy.it.model.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private static final String NULL_EXPENSE_MSG = "Expense can not be null";
    private static ExpenseServiceImpl instance;
    private final ExpenseDao expenseDao;

    private ExpenseServiceImpl() {
        expenseDao = ExpenseDao.retrieve();
    }

    public static ExpenseServiceImpl getInstance() {
        if (instance == null) {
            instance = new ExpenseServiceImpl();
        }
        return instance;
    }

    @Override
    public Expense save(Expense expense) {
        if (expense == null) {
            throw new UnknownEntityException(NULL_EXPENSE_MSG);
        }
        return expenseDao.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expenseDao.findAll();
    }
}
