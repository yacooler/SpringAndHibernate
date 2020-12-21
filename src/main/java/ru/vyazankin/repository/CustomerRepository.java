package ru.vyazankin.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Customer;

import java.util.List;
import java.util.Optional;


@Component
public class CustomerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer getCustomer(long customerId){
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, customerId);
        return customer;
    }

    public Optional<List<Customer>> getCustomers(){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.createQuery("from Customer", Customer.class).getResultList());
    }
}
