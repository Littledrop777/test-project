package by.academy.it.service;

import by.academy.it.model.Expense;
import by.academy.it.service.impl.ExpenseServiceImpl;

import java.util.List;

public interface ExpenseService {

    Expense save(Expense expense);

    List<Expense> findAll();

    static ExpenseService retrieve() {
        return ExpenseServiceImpl.getInstance();
    }

}
