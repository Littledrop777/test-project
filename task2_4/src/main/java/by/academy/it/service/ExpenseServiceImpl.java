package by.academy.it.service;

import by.academy.it.dao.ExpenseDao;
import by.academy.it.exception.EntityNotFoundException;
import by.academy.it.exception.UnknownEntityException;
import by.academy.it.model.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private static final String EXPENSE_WITH_NUM_NOT_FOUND_MSG = "Expense with num=%d does not exist";
    public static final String ENTITY_WITH_NUM_NULL_MSG = "Expense can not be with num null";
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

    @Override
    public Expense findById(Integer num) {
        if (num == null) {
            throw new UnknownEntityException(ENTITY_WITH_NUM_NULL_MSG);
        }
        return expenseDao.findByNum(num)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXPENSE_WITH_NUM_NOT_FOUND_MSG, num)));
    }

    @Override
    public void update(Expense expense) {
        if (expense == null) {
            throw new UnknownEntityException(NULL_EXPENSE_MSG);
        }
        expenseDao.update(expense);
    }

    @Override
    public void delete(Integer num) {
        if (num == null) {
            throw new UnknownEntityException(ENTITY_WITH_NUM_NULL_MSG);
        }
        expenseDao.delete(num);
    }
}
