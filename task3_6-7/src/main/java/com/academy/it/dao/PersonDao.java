package com.academy.it.dao;

import com.academy.it.dto.PersonDataDto;
import com.academy.it.model.Address;
import com.academy.it.model.Person;
import com.academy.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class PersonDao {

    private final SessionFactory sessionFactory;
    private static final String FIND_BY_ID_ALL_DATA_SQL =
            "SELECT new com.academy.it.dto.PersonDataDto(p.id, p.name, p.surname, ad.city, ad.street, ad.postalCode, co.companyName) " +
                    "FROM Person as p " +
                    "JOIN p.address as ad " +
                    "LEFT JOIN p.company co " +
                    "WHERE p.id =:person_id";

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
            Address address = person.getAddress();
            address.setPerson(person);
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

    public Person findById(Long id) {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    public PersonDataDto findPersonWithAllDataById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(FIND_BY_ID_ALL_DATA_SQL);
        query.setParameter("person_id", id);
        PersonDataDto person = (PersonDataDto) query.uniqueResult();
        session.close();
        return person;
    }

    public List<Person> findAll() {
        Session session = sessionFactory.openSession();
        List<Person> people = session.createQuery("FROM Person", Person.class).list();
        session.close();
        return people;
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
