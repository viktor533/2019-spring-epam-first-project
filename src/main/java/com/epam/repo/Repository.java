package com.epam.repo;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface Repository<T, K> {
    T save(T item);

    T removeById(K id) throws IllegalArgumentException;

    T findById(K id) throws IllegalArgumentException;

    T update(T item) throws IllegalArgumentException;

    Iterable<T> findAll();

    default Iterable<T> saveAll(T... items) {
        return Arrays.stream(items)
                .map(this::save)
                .collect(Collectors.toList());
    }

    default Iterable<T> removeAllById(K... ids) {
        return Arrays.stream(ids)
                .map(this::removeById)
                .collect(Collectors.toList());
    }

    default Iterable<T> findAllById(K... ids) {
        return Arrays.stream(ids)
                .map(this::findById)
                .collect(Collectors.toList());
    }

    default Iterable<T> updateAll(T... items) {
        return Arrays.stream(items)
                .map(this::update)
                .collect(Collectors.toList());
    }
}
