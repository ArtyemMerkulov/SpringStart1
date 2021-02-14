package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        return em.createNamedQuery("allUsers")
                .getResultList();
    }

    public User findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        return em.find(User.class, id);
    }

    public void insert(User user) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
    }

    public void update(User user) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("UPDATE User SET username=:username, password=:password, email=:email WHERE id=:id")
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .setParameter("email", user.getEmail())
                .setParameter("id", user.getId())
                .executeUpdate();

        em.getTransaction().commit();
    }

    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();

        em.getTransaction().commit();
    }
}
