package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.repo.HotelRepositoryImpl;
import lombok.SneakyThrows;

public class HotelService {
    HotelRepositoryImpl hotelRepository = new HotelRepositoryImpl();

    /**
     * Saves hotel into the database
     *
     * @param hotel
     * @return Hotel object that has been saved into the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Hotel save(Hotel hotel) throws IllegalArgumentException {
        return hotelRepository.save(hotel);
    }

    /**
     * Removes Hotel from the database by its id
     *
     * @param id
     * @return Hotel object that has been removed from the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Hotel removeById(Long id) throws IllegalArgumentException {
        return hotelRepository.removeById(id);
    }

    /**
     * Returns Hotel from the database by its id
     *
     * @param id
     * @return Hotel object from the database by its id
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Hotel findById(Long id) throws IllegalArgumentException {
        return hotelRepository.findById(id);
    }

    /**
     * Updates Hotel in the database
     *
     * @param hotel
     * @return Hotel object that has been updated in the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Hotel update(Hotel hotel) throws IllegalArgumentException {
        return hotelRepository.update(hotel);
    }

    /**
     * Finds all Hotels from database
     *
     * @return Iterable of all Hotel objects in the database
     */
    @SneakyThrows
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    /**
     * Saves the array of Hotels into the database
     *
     * @param hotels
     * @return Iterable of all Hotel objects that have been saved in the database
     */
    public Iterable<Hotel> saveAll(Hotel... hotels) {
        return hotelRepository.saveAll(hotels);
    }

    /**
     * Removes all Hotels from the database by their ids
     *
     * @param ids
     * @return Iterable of all Hotel objects that have been removed from the database by their ids
     */
    public Iterable<Hotel> removeAllById(Long... ids) {
        return hotelRepository.removeAllById(ids);
    }

    /**
     * Finds all Hotels from the database by their ids
     *
     * @param ids
     * @return Iterable of all Hotel objects by their ids
     */
    public Iterable<Hotel> findAllById(Long... ids) {
        return hotelRepository.findAllById(ids);
    }

    /**
     * Updates all Hotel that have been passed into the method in the database
     *
     * @param hotels
     * @return Iterable of all Hotels that have been updated in the database
     */
    public Iterable<Hotel> updateAll(Hotel... hotels) {
        return hotelRepository.updateAll(hotels);
    }
}
