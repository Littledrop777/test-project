package by.academy.it.service;

import by.academy.it.model.Receiver;
import by.academy.it.service.impl.ReceiverServiceImpl;

import java.util.List;

public interface ReceiverService {

    Receiver save(Receiver receiver);

    Receiver findByName(String name);

    List<Receiver> findAll();

    static ReceiverService retrieve() {
        return ReceiverServiceImpl.getInstance();
    }
}
