package ru.vyazankin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.vyazankin.entity.Customer;
import ru.vyazankin.entity.Product;
import ru.vyazankin.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Optional<List<Customer>> getCustomerList(long productId){
        Product product = productRepository.getProduct(productId);
        if (product == null) return Optional.empty();
        return Optional.of(product.getProductPriceList()
                .stream()
                .flatMap(price -> price.getCustomerList().stream())
                .distinct()
                .collect(Collectors.toList()));
    }

    public Optional<List<Product>> getProducts(){
        return productRepository.getProducts();
    }
}
