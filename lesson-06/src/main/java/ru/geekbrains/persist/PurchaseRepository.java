package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class PurchaseRepository {

    private final EntityManagerFactory emFactory;

    public PurchaseRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void insert(Purchase purchase) {
        executeInTransaction(em -> em.persist(purchase));
    }

    public List<Purchase> getUsersPurshasesProducts(Long user_id) {
        return executeForEntityManager(
                em -> em.createQuery("from Purchase p where user_id = :id", Purchase.class)
                        .setParameter("id", user_id)
                        .getResultList()
        );
    }


}
