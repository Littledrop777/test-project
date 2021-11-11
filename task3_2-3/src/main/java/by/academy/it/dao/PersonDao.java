package by.academy.it.dao;

import by.academy.it.pojo.Person;
import by.academy.it.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDao {

    private final SessionFactory sessionFactory;

    public PersonDao() {
        this(HibernateUtil.getSessionFactory());
    }

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String savePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(person);
            session.flush();
            session.refresh(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return person.getId();
    }

    public Person loadPerson(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Person person = null;
        try {
            transaction = session.beginTransaction();
            person = session.load(Person.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            //e.printStackTrace();
        }
        return person;
    }

    public Person readPerson(String id) {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    public List<Person> readAllPeople() {
        Session session = sessionFactory.openSession();
        List<Person> personList = session.createQuery("from Person", Person.class).getResultList();
        session.close();
        return personList;
    }

    public void deletePerson(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Person person = session.get(Person.class, id);
            session.delete(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean addAndDeletePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(person);
            session.flush();
            session.refresh(person);
            session.delete(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return person.getId() != null && readPerson(person.getId()) == null;
    }
}
