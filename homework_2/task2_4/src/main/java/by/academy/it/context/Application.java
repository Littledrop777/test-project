package by.academy.it.context;

import by.academy.it.exception.InvalidateArgumentException;
import by.academy.it.model.Expense;
import by.academy.it.model.Receiver;
import by.academy.it.service.ExpenseService;
import by.academy.it.service.ReceiverService;
import by.academy.it.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Application {

    private static final String INCORRECT_INPUT_MSG = "Incorrect input data. You should enter date(dd-MM-yyyy), receiver, value";
    private static final String WRONG_DATE_OR_VALUE_MSG = "Wrong date or value. Date format dd-MM-yyy and value must be greater than 0";

    public static void start(String[] args) {
        if (args.length != 3) {
            throw new InvalidateArgumentException(INCORRECT_INPUT_MSG);
        }
        Validator validator = Validator.take();
        String dateString = args[0];
        String receiverName = args[1];
        double value = Double.parseDouble(args[2]);
        if (!validator.isValidDate(dateString) || !validator.isValidValue(value)) {
            throw new InvalidateArgumentException(WRONG_DATE_OR_VALUE_MSG);
        }
        LocalDate date = parseDateArgs(dateString);
        ReceiverService receiverService = ReceiverService.retrieve();
        ExpenseService expenseService = ExpenseService.retrieve();
        Receiver receiver = receiverService.findByName(receiverName);
        if (receiver == null) {
            receiver = receiverService.save(new Receiver(receiverName));
        }
        Expense expense = expenseService.save(new Expense(date, receiver, value));
        List<Expense> expenses = expenseService.findAll();
        printExpenses(expenses);
    }

    private static void printExpenses(List<Expense> expenses) {
        expenses.forEach(expense -> System.out.format("%3d | %11s | %20s | %.2f\n",
                expense.getNum(),
                expense.getDate(),
                expense.getReceiver().getName(),
                expense.getValue()));
    }

    private static LocalDate parseDateArgs(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
