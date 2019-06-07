package com.epam.service;

import com.epam.repo.Repository;
import lombok.SneakyThrows;

abstract public class BaseService<T, K> {
    private final Repository<T, K> repository;

    public BaseService(Repository<T, K> repository) {
        this.repository = repository;
    }

    /**
     * Saves some into the database
     *
     * @param some that we want to save in the database
     * @return T object that has been saved into the database
     */
    @SneakyThrows
    public T save(T some) {
        return repository.save(some);
    }

    /**
     * Removes some from the database by its id
     *
     * @param id of the some that want to delete from the database
     * @return T object that has been removed from the database
     */
    @SneakyThrows
    public T removeById(K id) {
        return repository.removeById(id);
    }

    /**
     * Returns some from the database by its id
     *
     * @param id of the some that want to find from the database
     * @return T object from the database by its id
     */
    @SneakyThrows
    public T findById(K id) {
        return repository.findById(id);
    }

    /**
     * Updates some in the database
     *
     * @param some that we want to update in the database
     * @return T object that has been updated in the database
     */
    @SneakyThrows
    public T update(T some) {
        return repository.update(some);
    }

    /**
     * Finds all somes from the database
     *
     * @return Iterable of all T objects in the database
     */
    @SneakyThrows
    public Iterable<T> findAll() {
        return repository.findAll();
    }

    /**
     * Saves the array of somes into the database
     *
     * @param somes that we want to save in the database
     * @return Iterable of all T objects that have been saved in the database
     */
    public Iterable<T> saveAll(T... somes) {
        return repository.saveAll(somes);
    }

    /**
     * Removes all somes from the database by their ids
     *
     * @param ids of the somes that want to remove from the database
     * @return Iterable of all T objects that have been removed from the database by their ids
     */
    public Iterable<T> removeAllById(K... ids) {
        return repository.removeAllById(ids);
    }

    /**
     * Finds all somes from the database by their ids
     *
     * @param ids of the somes that want to find in the database
     * @return Iterable of all T objects by their ids
     */
    public Iterable<T> findAllById(K... ids) {
        return repository.findAllById(ids);
    }

    /**
     * Updates all some that have been passed into the method in the database
     *
     * @param somes that we want to update in the database
     * @return Iterable of all T objects that have been updated in the database
     */
    public Iterable<T> updateAll(T... somes) {
        return repository.updateAll(somes);
    }


}
