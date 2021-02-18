package ru.geekbrains.springbootapp.persist;

import javax.validation.constraints.*;

public class Product {

    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    private String description;

    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0", message = "Цена не может быть меньше 0")
    private Float price;

    public Product() {
    }

    public Product(String name, String description, Float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
