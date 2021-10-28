package by.academy.it;

import by.academy.it.service.Service;
import by.academy.it.dto.ExpenseDto;
import by.academy.it.view.Print;

public class Main {

    public static void main(String[] args) {
        Service service = Service.retrieve();
        Print.printReceivers(service.findAllWithSumPay());
        Print.printExpenses(service.findAllPaymentByDateWithMaxPay());
        ExpenseDto payment = service.findMaxPaymentByDayWithMaxSum();
        Print.printMaxPayment(payment);
    }
}
