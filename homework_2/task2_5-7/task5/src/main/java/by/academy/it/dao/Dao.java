package by.academy.it.dao;

import by.academy.it.model.Entity;

import java.util.List;

public interface Dao<T extends Entity> {

    T save(T t);

    List<T> findAll();

}
