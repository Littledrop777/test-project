package by.academy.it.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@DiscriminatorValue("S")
public class Student extends Person{

    private String faculty;
    private Double avgMark;

    public Student(String name, String surname, String faculty, Double avgMark) {
        super(name, surname);
        this.faculty = faculty;
        this.avgMark = avgMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(faculty, student.faculty) && Objects.equals(avgMark, student.avgMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty, avgMark);
    }
}
