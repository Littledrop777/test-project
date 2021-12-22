package by.academy.it.dao;

import by.academy.it.model.Employee;
import by.academy.it.model.Person;
import by.academy.it.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDaoTest extends BaseTest {

    private PersonDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new PersonDao(sessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void savePerson() {
        Person person = new Person("Person_name1", "Person_surname1");
        Serializable id = dao.savePerson(person);
        assertNotNull(id);
        assertEquals(id, person.getId());
    }

    @Test
    public void saveEmployee() {
        Employee employee = new Employee("Employee_name1", "Employee_surname1", "manager", new BigDecimal(1500));
        Serializable id = dao.savePerson(employee);
        assertNotNull(id);
        assertEquals(id, employee.getId());
    }

    @Test
    public void saveStudent() {
        Student employee = new Student("Student_name1", "Student_surname1", "marketing", 7.9);
        Serializable id = dao.savePerson(employee);
        assertNotNull(id);
        assertEquals(id, employee.getId());
    }

    @Test
    public void findByIdPerson() {
        Person person = new Person("Person_name2", "Person_surname2");
        String id = (String) dao.savePerson(person);
        Person savedPerson = dao.findById(id);
        assertNotNull(savedPerson);
        assertEquals(id, savedPerson.getId());

    }

    @Test
    public void findByIdStudent() {
        Employee employee = new Employee("Employee_name2", "Employee_surname2", "accountant", new BigDecimal(1750));
        String id = (String) dao.savePerson(employee);
        Person savedEmployee = dao.findById(id);
        assertNotNull(savedEmployee);
        assertEquals(id, savedEmployee.getId());
    }

    @Test
    public void findByIdEmployee() {
        Student employee = new Student("Student_name2", "Student_surname2", "management", 8.7);
        String id = (String) dao.savePerson(employee);
        Person savedPerson = dao.findById(id);
        assertNotNull(savedPerson);
        assertEquals(id, savedPerson.getId());
    }
}