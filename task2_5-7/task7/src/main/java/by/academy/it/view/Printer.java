package by.academy.it.view;

import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;

import java.util.List;

public class Printer {
    public static void printReceivers(List<Receiver> receivers) {
        System.out.println("Receivers");
        receivers.forEach(receiver -> System.out.format("%3d | %20s\n",
                receiver.getNum(),
                receiver.getName()));
        System.out.println();
    }

    public static void printExpenses(List<Expense> expenses) {
        System.out.println("Expenses");
        expenses.forEach(expense -> System.out.format("%3d | %10s | %15s | %.2f\n",
                expense.getNum(),
                expense.getDate(),
                expense.getReceiver().getName(),
                expense.getValue()));
        System.out.println();
    }

    public static void printExpense(Expense expense){
        System.out.format("Receiver id=%d %s %s %.2f\n ",
                expense.getNum(),
                expense.getDate(),
                expense.getReceiver().getName(),
                expense.getValue());
    }

    public static void printReceiver(Receiver receiver){
        System.out.format("Expense id=%d %s\n ",
                receiver.getNum(),
                receiver.getName());
    }
}
