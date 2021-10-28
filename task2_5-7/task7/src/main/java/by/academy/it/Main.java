package by.academy.it;

import by.academy.it.dao.Dao;
import by.academy.it.dao.MyDao;
import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;
import by.academy.it.view.Printer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Dao myDao = MyDao.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse("22-11-2021", formatter);
        String name = "ProStore";
        Receiver receiver = myDao.getReceiverByName(name).orElse(null);
        if(receiver == null){
            receiver = myDao.addReceiver(new Receiver(name));
        }
        Expense expense = myDao.addExpense(new Expense(date, receiver, 225.13));
        Printer.printReceivers(myDao.getReceivers());
        Printer.printExpenses(myDao.getExpenses());
        Printer.printReceiver(Objects.requireNonNull(myDao.getReceiver(receiver.getNum()).orElse(null)));
        Printer.printExpense(Objects.requireNonNull(myDao.getExpense(expense.getNum()).orElse(null)));
    }
}
