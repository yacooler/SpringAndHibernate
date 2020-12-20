package ru.vyazankin.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Product;

@Component(value = "test")
public class Test {
    @Autowired
    private SessionFactory sessionFactory;

    public void select(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("FROM Product", Product.class).getResultList().forEach(System.out::println);
        session.getTransaction().rollback();
        session.close();
    }

}
