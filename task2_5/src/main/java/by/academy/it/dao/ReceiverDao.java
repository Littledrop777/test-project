package by.academy.it.dao;

import by.academy.it.dao.impl.ReceiverDaoImpl;
import by.academy.it.model.Receiver;

public interface ReceiverDao extends Dao<Receiver> {

    static ReceiverDao retrieve() {
        return ReceiverDaoImpl.getInstance();
    }
}
