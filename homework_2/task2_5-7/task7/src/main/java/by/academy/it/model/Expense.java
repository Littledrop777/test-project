package by.academy.it.model;

import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private int num;
    private final LocalDate date;
    private final Receiver receiver;
    private final double value;

    public Expense(LocalDate date, Receiver receiver, double value) {
        this.date = date;
        this.receiver = receiver;
        this.value = value;
    }

    public Expense(int num, LocalDate date, Receiver receiver, double value) {
        this.num = num;
        this.date = date;
        this.receiver = receiver;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public Expense updateWithNum(int num) {
        return new Expense(num, date, receiver, value);
    }

    public LocalDate getDate() {
        return date;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return num == expense.num && Double.compare(expense.value, value) == 0 && Objects.equals(date, expense.date) && Objects.equals(receiver, expense.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, date, receiver, value);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "num=" + num +
                ", date=" + date +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }
}
