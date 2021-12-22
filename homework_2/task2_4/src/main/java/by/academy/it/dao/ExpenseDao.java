package by.academy.it.dao;

import by.academy.it.dao.impl.ExpenseDaoImpl;
import by.academy.it.model.Expense;

public interface ExpenseDao extends Dao<Expense> {

    static ExpenseDao retrieve() {
        return ExpenseDaoImpl.getInstance();
    }
}
