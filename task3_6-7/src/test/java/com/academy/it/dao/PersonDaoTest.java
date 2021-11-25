package com.academy.it.dao;

import com.academy.it.dto.PersonDataDto;
import com.academy.it.model.Address;
import com.academy.it.model.Company;
import com.academy.it.model.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

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
        Address address = new Address("Gomel", "Mazurova", "246017");
        Person person = new Person("Solovey", "Stanislav");
        person.setAddress(address);
        address.setPerson(person);
        Serializable id = dao.savePerson(person);
        assertNotNull(id);
        assertEquals(id, person.getId());
        dao.deletePerson(person.getId());
    }

    @Test
    public void findById() {
        Address address = new Address("Brest", "Moskovskaya", "224001");
        Person person = new Person("Ivanova", "Svetlana");
        person.setAddress(address);
        address.setPerson(person);
        Long id = (Long) dao.savePerson(person);
        Person personWithId = dao.findById(id);
        assertNotNull(personWithId);
        assertEquals(id, personWithId.getId());
        dao.deletePerson(person.getId());
    }

    @Test
    public void findPersonWithAllDataById() {
        CompanyDao companyDao = new CompanyDao(sessionFactory);
        Address address = new Address("Minsk", "Plehanova", "220095");
        Person person = new Person("Plehanova", "Anastasia");
        Company company = new Company("Academy");
        person.setAddress(address);
        person.setCompany(company);
        address.setPerson(person);
        companyDao.saveCompany(company);
        Long id = (Long) dao.savePerson(person);
        PersonDataDto personWithAllData = dao.findPersonWithAllDataById(id);
        assertNotNull(personWithAllData);
        assertEquals(id, personWithAllData.getId());
        companyDao.deleteCompany(company.getId());
        dao.deletePerson(person.getId());
    }

    @Test
    public void deletePerson() {
        CompanyDao companyDao = new CompanyDao(sessionFactory);
        Address address = new Address("Soligorsk", "Mira", "221133");
        Person person = new Person("Petrov", "Gleb");
        Company company = new Company("MTC");
        person.setAddress(address);
        address.setPerson(person);
        person.setCompany(company);
        companyDao.saveCompany(company);
        Long id = (Long) dao.savePerson(person);
        dao.deletePerson(id);
        assertNull(dao.findById(id));
        companyDao.deleteCompany(company.getId());
        dao.deletePerson(person.getId());
    }

}