package ru.geekbrains.persist;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchased_products")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @Column(name = "purchase_timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "purchase_user_fk"),
            insertable = false,
            updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "purchase_product_fk"),
            insertable = false,
            updatable = false)
    private Product product;

    public Purchase() {
    }

    public Purchase(Long user_id, Long product_id, LocalDateTime timestamp) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
