package by.academy.it.service;

import by.academy.it.dto.ExpenseDto;
import by.academy.it.dto.ReceiverDto;

import java.util.List;

public interface Service {

    List<ReceiverDto> findAllWithSumPay();

    List<ExpenseDto> findAllPaymentByDateWithMaxPay();

    ExpenseDto findMaxPaymentByDayWithMaxSum();

    static Service retrieve() {
        return ServiceImpl.getInstance();
    }
}
