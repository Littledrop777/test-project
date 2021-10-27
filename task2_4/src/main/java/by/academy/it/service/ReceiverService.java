package by.academy.it.service;

import by.academy.it.model.Receiver;

public interface ReceiverService {

    Receiver save(Receiver receiver);

    Receiver findByName(String name);

    static ReceiverService retrieve() {
        return ReceiverServiceImpl.getInstance();
    }
}
