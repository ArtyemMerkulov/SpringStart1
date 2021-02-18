package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        return em.createNamedQuery("allProducts", Product.class))
                .getResultList();
    }

    public Product findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        return em.find(Product.class, id);
    }

    public void insert(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(product);

        em.getTransaction().commit();
    }

    public Product saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(product);

        em.getTransaction().commit();

        return product;
    }

    public void deleteById(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Product WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();

        em.getTransaction().commit();
    }
}
