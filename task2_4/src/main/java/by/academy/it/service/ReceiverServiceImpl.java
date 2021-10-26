package by.academy.it.service;

import by.academy.it.dao.ReceiverDao;
import by.academy.it.exception.EntityNotFoundException;
import by.academy.it.exception.UnknownEntityException;
import by.academy.it.model.Receiver;

import java.util.List;

public class ReceiverServiceImpl implements ReceiverService {

    private static final String EXPENSE_WITH_NUM_NOT_FOUND_MSG = "Receiver with num=%d does not exist";
    public static final String ENTITY_WITH_NUM_NULL_MSG = "Receiver can not be with num null";
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
    public List<Receiver> findAll() {
        return receiverDao.findAll();
    }

    @Override
    public Receiver findById(Integer num) {
        if (num == null) {
            throw new UnknownEntityException(ENTITY_WITH_NUM_NULL_MSG);
        }
        return receiverDao.findByNum(num)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXPENSE_WITH_NUM_NOT_FOUND_MSG, num)));
    }

    @Override
    public void update(Receiver receiver) {
        if (receiver == null) {
            throw new UnknownEntityException(NULL_EXPENSE_MSG);
        }
        receiverDao.update(receiver);
    }

    @Override
    public void delete(Integer num) {
        if (num == null) {
            throw new UnknownEntityException(ENTITY_WITH_NUM_NULL_MSG);
        }
        receiverDao.delete(num);
    }
}
