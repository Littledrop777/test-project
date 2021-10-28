package by.academy.it.dto;

import java.util.Objects;

public class ReceiverDto {

    private final String name;
    private final double sum;

    public ReceiverDto(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiverDto that = (ReceiverDto) o;
        return Double.compare(that.sum, sum) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sum);
    }

    @Override
    public String toString() {
        return "ReceiverDto{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
