package by.academy.it.view;

import by.academy.it.dto.ExpenseDto;
import by.academy.it.dto.ReceiverDto;

import java.util.List;

public class Print {

    public static void printReceivers(List<ReceiverDto> receivers) {
        System.out.println("Receivers with sum payments");
        receivers.forEach(receiver -> System.out.format("%20s | %.2f\n",
                receiver.getName(),
                receiver.getSum()));
        System.out.println();
    }

    public static void printExpenses(List<ExpenseDto> expenses) {
        System.out.println("Sum of payments for the day when the largest payment was");
        expenses.forEach(expense -> System.out.format("%10s | %.2f\n",
                expense.getDate(),
                expense.getSum()));
        System.out.println();
    }

    public static void printMaxPayment(ExpenseDto expense){
        System.out.format("Max payment for the day when the largest sum was\n " +
                "%s  %.2f", expense.getDate(), expense.getSum());
    }
}
