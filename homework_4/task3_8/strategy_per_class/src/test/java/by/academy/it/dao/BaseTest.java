package by.academy.it.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;

public class BaseTest {
    static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_per_class-test.cfg.xml")
                .build();
        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }
}
