package ru.vyazankin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Customer;
import ru.vyazankin.entity.Price;
import ru.vyazankin.entity.Product;
import ru.vyazankin.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Optional<List<Price>> getCustomPriceList(long customerId){
        Customer customer = customerRepository.getCustomer(customerId);
        if (customer == null) return Optional.empty();
        return Optional.of(customer.getCustomPriceList());
    }

    public Optional<List<Product>> getProductList(long customerId){
        Customer customer = customerRepository.getCustomer(customerId);
        if (customer == null) return Optional.empty();
        return Optional.of(customer.getCustomPriceList()
                                .stream()
                                .map(price -> price.getProduct())
                                .collect(Collectors.toList()));

    }

    public Optional<List<Customer>> getCustomers(){
        return customerRepository.getCustomers();
    }

}
