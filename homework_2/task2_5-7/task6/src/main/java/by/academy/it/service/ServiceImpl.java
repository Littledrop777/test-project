package by.academy.it.service;

import by.academy.it.dao.Dao;
import by.academy.it.dto.ExpenseDto;
import by.academy.it.dto.ReceiverDto;

import java.util.List;

public class ServiceImpl implements Service {

    private static ServiceImpl instance;
    private final Dao dao;

    private ServiceImpl() {
        this.dao = Dao.retrieve();
    }

    public static ServiceImpl getInstance() {
        if (instance == null) {
            instance = new ServiceImpl();
        }
        return instance;
    }

    @Override
    public List<ReceiverDto> findAllWithSumPay() {
        return dao.findAllWithSumPay();
    }

    @Override
    public List<ExpenseDto> findAllPaymentByDateWithMaxPay() {
        return dao.findAllPaymentByDateWithMaxPay();
    }

    @Override
    public ExpenseDto findMaxPaymentByDayWithMaxSum() {
        return dao.findMaxPaymentByDayWithMaxSum().orElse(null);
    }
}
