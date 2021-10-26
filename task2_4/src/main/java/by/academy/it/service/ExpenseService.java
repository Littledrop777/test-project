package by.academy.it.service;

import by.academy.it.model.Expense;

import java.util.List;

public interface ExpenseService {

    Expense save(Expense expense);

    List<Expense> findAll();

    Expense findById(Integer num);

    void update(Expense expense);

    void delete(Integer num);

    static ExpenseService retrieve() {
        return ExpenseServiceImpl.getInstance();
    }

}
