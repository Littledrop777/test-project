package com.academy.it.dao;

import com.academy.it.model.Company;
import com.academy.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class CompanyDao {

    private final SessionFactory sessionFactory;

    public CompanyDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Serializable saveCompany(Company company) {
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        Serializable id = null;

        try {
            tr = session.beginTransaction();
            id = session.save(company);
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

    public Company findById(Long id) {
        Session session = sessionFactory.openSession();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    public List<Company> findAll() {
        Session session = sessionFactory.openSession();
        List<Company> companyList = session.createQuery("FROM Company", Company.class).list();
        session.close();
        return companyList;
    }

    public void deleteCompany(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Company company = session.get(Company.class, id);
            session.delete(company);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) session.close();
        }
    }
}
