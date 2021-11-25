package by.academy.it.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person {

    private String role;
    private BigDecimal salary;

    public Employee(String name, String surname, String role, BigDecimal salary) {
        super(name, surname);
        this.role = role;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(role, employee.role) && Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, salary);
    }
}
