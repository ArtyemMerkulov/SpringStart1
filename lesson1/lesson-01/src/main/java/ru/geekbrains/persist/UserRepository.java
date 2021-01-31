package ru.geekbrains.persist;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private Map<Long, User> userMap = new ConcurrentHashMap<>();

    private AtomicLong index = new AtomicLong(0);

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void insert(User user) {
        user.setId(index.incrementAndGet());
        userMap.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(Long id) {
        userMap.remove(id);
    }
}
