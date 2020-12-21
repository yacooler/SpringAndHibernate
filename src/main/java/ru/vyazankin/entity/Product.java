package ru.vyazankin.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    private static final long serialVersionUID = -397775056591704824L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<Price> productPriceList;


    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Price> getProductPriceList() {
        return productPriceList;
    }

    public void setProductPriceList(List<Price> productPriceList) {
        this.productPriceList = productPriceList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", priceList=").append(productPriceList);
        sb.append('}');
        return sb.toString();
    }
}
