package by.academy.it.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@DiscriminatorValue("E")
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
