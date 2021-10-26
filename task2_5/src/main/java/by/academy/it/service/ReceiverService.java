package by.academy.it.service;

import by.academy.it.model.Receiver;

import java.util.List;

public interface ReceiverService {

    Receiver save(Receiver receiver);

    List<Receiver> findAll();

    static ReceiverService retrieve() {
        return ReceiverServiceImpl.getInstance();
    }
}
