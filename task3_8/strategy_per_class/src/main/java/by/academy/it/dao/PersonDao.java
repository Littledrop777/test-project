package by.academy.it.dao;

import by.academy.it.model.Person;
import by.academy.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public class PersonDao {

    private final SessionFactory sessionFactory;

    public PersonDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Serializable savePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        Serializable id = null;

        try {
            tr = session.beginTransaction();
            id = session.save(person);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public Person findById(String id) {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    public void deletePerson(Long id) {
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
}
