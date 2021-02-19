package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.*;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        init(emFactory);
    }


    public static void init(EntityManagerFactory emFactory) {
        UserRepository ur = new UserRepository(emFactory);
        ProductRepository pr = new ProductRepository(emFactory);
        PurchaseRepository pur = new PurchaseRepository(emFactory);

        User u1 = new User("u1");
        User u2 = new User("u2");
        User u3 = new User("u3");

        Product p1 = new Product("p1", 1f);
        Product p2 = new Product("p2", 2f);
        Product p3 = new Product("p3", 3f);
        Product p4 = new Product("p4", 4f);
        Product p5 = new Product("p5", 5f);
        Product p6 = new Product("p6", 6f);

        LocalDate ld = LocalDate.now();

        Purchase pur1 = new Purchase(u1.getId(), p1.getId(), LocalDateTime.of(ld, LocalTime.of(1, 1)));
        Purchase pur2 = new Purchase(u1.getId(), p2.getId(), LocalDateTime.of(ld, LocalTime.of(1, 2)));
        Purchase pur3 = new Purchase(u1.getId(), p3.getId(), LocalDateTime.of(ld, LocalTime.of(1, 3)));
        Purchase pur4 = new Purchase(u2.getId(), p4.getId(), LocalDateTime.of(ld, LocalTime.of(2, 1)));
        Purchase pur5 = new Purchase(u2.getId(), p5.getId(), LocalDateTime.of(ld, LocalTime.of(2, 2)));
        Purchase pur6 = new Purchase(u3.getId(), p6.getId(), LocalDateTime.of(ld, LocalTime.of(3, 1)));

        ur.insert(u1);
        ur.insert(u2);
        ur.insert(u3);

        pr.insert(p1);
        pr.insert(p2);
        pr.insert(p3);
        pr.insert(p4);
        pr.insert(p5);
        pr.insert(p6);

        pur.insert(pur1);
        pur.insert(pur2);
        pur.insert(pur3);
        pur.insert(pur4);
        pur.insert(pur5);
        pur.insert(pur6);
    }
}
