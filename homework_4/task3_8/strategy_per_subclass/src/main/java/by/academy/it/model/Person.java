package by.academy.it.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

 @Id
 @Column(name = "id")
 @GeneratedValue(generator = "uuid-generator")
 @GenericGenerator(name = "uuid-generator", strategy = "uuid")
 private String id;

 @Setter
 @Column(name = "p_name")
 private String name;

 @Setter
 @Column(name = "p_surname")
 private String surname;

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

