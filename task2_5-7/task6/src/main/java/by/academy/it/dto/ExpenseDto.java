package by.academy.it.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ExpenseDto {

    private final LocalDate date;
    private final double value;

    public ExpenseDto(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getSum() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDto that = (ExpenseDto) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }

    @Override
    public String toString() {
        return "ExpenseWithSum{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
