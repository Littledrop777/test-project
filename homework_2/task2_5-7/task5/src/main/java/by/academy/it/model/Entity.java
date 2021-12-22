package by.academy.it.model;

public interface Entity<T> {

    int getNum();

    T updateWithNum(int num);
}