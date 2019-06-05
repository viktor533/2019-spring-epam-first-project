package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.repo.Repository;
import lombok.SneakyThrows;

public class HotelService {
    private final Repository<Hotel, Long> hotelRepository;

    public HotelService(Repository<Hotel, Long> hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Saves hotel into the database
     *
     * @param hotel that we want to save in the database
     * @return Hotel object that has been saved into the database
     */
    @SneakyThrows
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    /**
     * Removes hotel from the database by its id
     *
     * @param id of the hotel that want to delete from the database
     * @return Hotel object that has been removed from the database
     */
    @SneakyThrows
    public Hotel removeById(Long id) {
        return hotelRepository.removeById(id);
    }

    /**
     * Returns hotel from the database by its id
     *
     * @param id of the hotel that want to find from the database
     * @return Hotel object from the database by its id
     */
    @SneakyThrows
    public Hotel findById(Long id) {
        return hotelRepository.findById(id);
    }

    /**
     * Updates hotel in the database
     *
     * @param hotel that we want to update in the database
     * @return Hotel object that has been updated in the database
     */
    @SneakyThrows
    public Hotel update(Hotel hotel) {
        return hotelRepository.update(hotel);
    }

    /**
     * Finds all hotels from the database
     *
     * @return Iterable of all Hotel objects in the database
     */
    @SneakyThrows
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    /**
     * Saves the array of hotels into the database
     *
     * @param hotels that we want to save in the database
     * @return Iterable of all Hotel objects that have been saved in the database
     */
    public Iterable<Hotel> saveAll(Hotel... hotels) {
        return hotelRepository.saveAll(hotels);
    }

    /**
     * Removes all hotels from the database by their ids
     *
     * @param ids of the hotels that want to remove from the database
     * @return Iterable of all Hotel objects that have been removed from the database by their ids
     */
    public Iterable<Hotel> removeAllById(Long... ids) {
        return hotelRepository.removeAllById(ids);
    }

    /**
     * Finds all hotels from the database by their ids
     *
     * @param ids of the hotels that want to find in the database
     * @return Iterable of all Hotel objects by their ids
     */
    public Iterable<Hotel> findAllById(Long... ids) {
        return hotelRepository.findAllById(ids);
    }

    /**
     * Updates all hotel that have been passed into the method in the database
     *
     * @param hotels that we want to update in the database
     * @return Iterable of all Hotel objects that have been updated in the database
     */
    public Iterable<Hotel> updateAll(Hotel... hotels) {
        return hotelRepository.updateAll(hotels);
    }
}
