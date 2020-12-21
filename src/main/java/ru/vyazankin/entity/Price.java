package ru.vyazankin.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "price")
public class Price implements Serializable {
    private static final long serialVersionUID = 8405452786938502747L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "price_date")
    @Type(type = "date")
    Date priceDate;

    @Column(name = "price")
    int price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "custom",
            joinColumns = @JoinColumn(name = "price_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customerList;


    public Price() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Price{");
        sb.append("id=").append(id);
        sb.append(", priceDate=").append(priceDate);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
