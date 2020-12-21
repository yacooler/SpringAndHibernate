package ru.vyazankin.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Customer;
import ru.vyazankin.entity.Product;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product getProduct(long productId){
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, productId);
        return product;
    }

    public Optional<List<Product>> getProducts(){
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.createQuery("from Product", Product.class).getResultList());
    }

}
