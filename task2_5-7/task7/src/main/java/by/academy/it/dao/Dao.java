package by.academy.it.dao;

import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;

import java.util.List;
import java.util.Optional;

public interface Dao {

    Optional<Receiver> getReceiver(int num);

    List<Receiver> getReceivers();

    Optional<Expense> getExpense(int num);

    List<Expense> getExpenses();

    Receiver addReceiver(Receiver receiver);

    Expense addExpense(Expense expense);

    Optional<Receiver> getReceiverByName(String name);
}
