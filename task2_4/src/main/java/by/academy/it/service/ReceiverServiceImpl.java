package by.academy.it.service;

import by.academy.it.dao.ReceiverDao;
import by.academy.it.exception.UnknownEntityException;
import by.academy.it.model.Receiver;

public class ReceiverServiceImpl implements ReceiverService {

    public static final String ENTITY_WITH_NAME_NULL_MSG = "Receiver can not be with name null";
    private static final String NULL_EXPENSE_MSG = "Receiver can not be null";
    private static ReceiverServiceImpl instance;
    private final ReceiverDao receiverDao;

    private ReceiverServiceImpl() {
        receiverDao = ReceiverDao.retrieve();
    }

    public static ReceiverServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReceiverServiceImpl();
        }
        return instance;
    }

    @Override
    public Receiver save(Receiver receiver) {
        if (receiver == null) {
            throw new UnknownEntityException(NULL_EXPENSE_MSG);
        }
        return receiverDao.save(receiver);
    }

    @Override
    public Receiver findByName(String name) {
        if (name == null) {
            throw new UnknownEntityException(ENTITY_WITH_NAME_NULL_MSG);
        }
        return receiverDao.findByName(name)
                .orElse(null);
    }
}
