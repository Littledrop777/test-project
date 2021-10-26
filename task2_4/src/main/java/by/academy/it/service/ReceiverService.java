package by.academy.it.service;

import by.academy.it.model.Receiver;
import by.academy.it.model.Receiver;

import java.util.List;

public interface ReceiverService {

    Receiver save(Receiver receiver);

    List<Receiver> findAll();

    Receiver findById(Integer num);

    void update(Receiver receiver);

    void delete(Integer num);

    static ReceiverService retrieve() {
        return ReceiverServiceImpl.getInstance();
    }
}
