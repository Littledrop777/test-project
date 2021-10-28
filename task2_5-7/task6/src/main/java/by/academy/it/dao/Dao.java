package by.academy.it.dao;

import by.academy.it.dto.ExpenseDto;
import by.academy.it.dto.ReceiverDto;

import java.util.List;
import java.util.Optional;

public interface Dao {

    List<ReceiverDto> findAllWithSumPay();

    List<ExpenseDto> findAllPaymentByDateWithMaxPay();

    Optional<ExpenseDto> findMaxPaymentByDayWithMaxSum();

    static Dao retrieve(){
        return DaoImpl.getInstance();
    }
}
