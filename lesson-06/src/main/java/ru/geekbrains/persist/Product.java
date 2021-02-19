package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Purchase> purchasedProducts;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    List<User> buyers;

    public Product() {
    }

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

//    public List<User> getUser() {
//        return user;
//    }
//
//    public void setUser(List<User> user) {
//        this.user = user;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
