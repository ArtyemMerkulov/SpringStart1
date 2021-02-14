package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;


import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        UserRepositoryDemo(emFactory);
        ProductRepositoryDemo(emFactory);
    }

    public static void ProductRepositoryDemo(EntityManagerFactory emFactory) {
        ProductRepository pr = new ProductRepository(emFactory);

        // INSERT1
        System.out.println("INSERT");

        pr.insert(new Product("product1", "description1", 1f));
        pr.insert(new Product("product2", "description2", 2f));
        pr.insert(new Product("product3", "description3", 3f));
        pr.insert(new Product("product4", "description4", 4f));

        // FIND ALL
        System.out.println("FIND ALL");

        List<Product> productList = pr.findAll();
        productList.forEach(System.out::println);

        // FIND BY ID
        System.out.println("FIND BY ID");

        Long productId = productList.get(0).getId();
        Product findProduct = pr.findById(productId);
        System.out.println(findProduct);

        // UPDATE
        System.out.println("UPDATE");

        findProduct.setName("product1000");
        pr.saveOrUpdate(findProduct);
        pr.findAll().forEach(System.out::println);

        // SAVE
        System.out.println("UPDATE");

        pr.saveOrUpdate(new Product("product6", "description6", 5f));
        pr.findAll().forEach(System.out::println);

        // DELETE
        System.out.println("DELETE");

        pr.deleteById(productId);
        pr.findAll().forEach(System.out::println);
    }

    public static void UserRepositoryDemo(EntityManagerFactory emFactory) {
        UserRepository ur = new UserRepository(emFactory);

        // INSERT
        System.out.println("INSERT");

        ur.insert(new User("user1", "password1", "email1"));
        ur.insert(new User("user2", "password2", "email2"));
        ur.insert(new User("user3", "password3", "email3"));
        ur.insert(new User("user4", "password4", "email4"));

        // FIND ALL
        System.out.println("FIND ALL");

        List<User> userList = ur.findAll();
        userList.forEach(System.out::println);

        // FIND BY ID
        System.out.println("FIND BY ID");

        Long userId = userList.get(0).getId();
        User findUser = ur.findById(userId);
        System.out.println(findUser);

        // UPDATE
        System.out.println("UPDATE");

        findUser.setUsername("user1000");
        ur.update(findUser);
        ur.findAll().forEach(System.out::println);

        // DELETE
        System.out.println("DELETE");

        ur.delete(userId);
        ur.findAll().forEach(System.out::println);
    }
}
