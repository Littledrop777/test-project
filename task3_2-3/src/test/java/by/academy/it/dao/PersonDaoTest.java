package by.academy.it.dao;

import by.academy.it.pojo.Person;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest {

    static SessionFactory sessionFactory;
    PersonDao personDao;

    @BeforeClass
    public static void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.person-test.cfg.xml")
                .build();
        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDao(sessionFactory);
    }

    @After
    public void tearDown() throws Exception {
        personDao = null;
    }

    @Test
    public void getInstance() {
        assertNotNull(personDao);
    }

    @Test
    public void savePersonTest() {
        Person person = new Person();
        person.setAge(17);
        person.setName("Hurry");
        person.setSurname("Potter");
        String id = personDao.savePerson(person);
        assertNotNull(id);
        Person savedPerson = personDao.readPerson(id);
        assertEquals(person, savedPerson);
        personDao.deletePerson(id);
    }

    @Test
    public void readPersonTest() {
        Person person = new Person();
        person.setAge(16);
        person.setName("HermioneGranger");
        person.setSurname("Granger");
        personDao.savePerson(person);
        Person readPerson = personDao.readPerson(person.getId());
        assertNotNull(readPerson);
        personDao.deletePerson(person.getId());
    }

    @Test
    public void readAllPeopleTest() {
        Person person = new Person();
        person.setAge(18);
        person.setName("Neville");
        person.setSurname("Longbottom");
        personDao.savePerson(person);
        List<Person> people = personDao.readAllPeople();
        assertEquals(1, people.size());
        personDao.deletePerson(person.getId());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void loadPersonTest() {
        Person person = new Person();
        person.setAge(75);
        person.setName("Albus");
        person.setSurname("Dumbledore");
        personDao.savePerson(person);

        Person loadPerson = personDao.loadPerson(person.getId());
        assertNotNull(loadPerson);

        Person notExistPerson = personDao.loadPerson("");
        assertNull(notExistPerson);
        personDao.deletePerson(person.getId());
    }

    @Test
    public void addAndDeletePersonTest() {
        Person person = new Person();
        person.setAge(75);
        person.setName("Albus");
        person.setSurname("Dumbledore");
        assertTrue(personDao.addAndDeletePerson(person));
    }
}