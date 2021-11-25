package com.academy.it.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "p_name")
    private String name;
    @Column(name = "p_surname")
    private String surname;
    @Setter
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Address address;
    @Setter
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
