package ru.geekbrains.persist;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findById(Long id);
    void insert(T o);
    void update(T o);
    void delete(Long id);
}
