package by.academy.it.dao;

import by.academy.it.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {

    T save(T t);

    List<T> findAll();

    Optional<T> findByNum(int id);

    void update(T t);

    void delete(int id);
}
