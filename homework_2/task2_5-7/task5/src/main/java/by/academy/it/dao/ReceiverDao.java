package by.academy.it.dao;

import by.academy.it.dao.impl.ReceiverDaoImpl;
import by.academy.it.model.Receiver;

import java.util.Optional;

public interface ReceiverDao extends Dao<Receiver> {

    Optional<Receiver> findByName(String name);

    static ReceiverDao retrieve() {
        return ReceiverDaoImpl.getInstance();
    }
}
