package ru.geekbrains;

import javax.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class ProductService implements Serializable {

    private static final Random random = new Random();

    private static final AtomicInteger currentId = new AtomicInteger(1);

    private static final String[] productsNames = new String[] {"Orange", "Apple", "Bread",
            "Berry", "Cherry", "Potato"};

    public ProductService() {
        System.out.println("EBANA B ROTOOOOTTT");
    }

    public List<Product> getProducts(Integer total) {
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            productList.add(new Product(
                    currentId.getAndIncrement(),
                    productsNames[random.nextInt(productsNames.length)],
                    ((float) Math.round(random.nextFloat() * 10000)) / 100
            ));
        }

        return productList;
    }
}
