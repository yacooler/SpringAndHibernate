package ru.vyazankin.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Customer;
import ru.vyazankin.entity.Product;
import ru.vyazankin.service.CustomerService;
import ru.vyazankin.service.ProductService;

@Component(value = "test")
public class Test {

    private CustomerService customerService;
    private ProductService productService;
    private SessionFactory sessionFactory;

    @Autowired
    public Test(CustomerService customerService, ProductService productService, SessionFactory sessionFactory) {
        this.customerService = customerService;
        this.productService = productService;
        this.sessionFactory = sessionFactory;
    }


    public void test(){
        try(Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            for (Product product: productService.getProducts().get()) {
                System.out.printf("---Кто купил %s---\n", product.getTitle());
                productService.getCustomerList(product.getId()).ifPresent(
                        customers -> customers
                                .stream()
                                .map(Customer::getName)
                                .forEach(System.out::println));
            }

            for (Customer customer:customerService.getCustomers().get()) {
                System.out.printf("---Что купил(а) %s--\n", customer.getName());
                customerService.getProductList(customer.getId()).ifPresent(
                        products -> products.stream()
                                .map(Product::getTitle)
                                .forEach(System.out::println));
            }


            for (Customer customer:customerService.getCustomers().get()){
                System.out.printf("---Что купил(а) %s и сколько это стоило на момент покупки---\n", customer.getName() );
                customerService.getCustomPriceList(customer.getId()).ifPresent(
                        prices -> prices.stream()
                                .map(price -> String.format("%s по цене %s цена на дату %s",price.getProduct().getTitle(), price.getPrice(), price.getPriceDate()))
                                .forEach(System.out::println));

            }

        }
    }

}
