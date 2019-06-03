package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.repo.HotelRepositoryImpl;
import lombok.SneakyThrows;

public class HotelService {
    HotelRepositoryImpl hotelRepository = new HotelRepositoryImpl();

    @SneakyThrows
    public Hotel save(Hotel hotel) throws IllegalArgumentException {
        return hotelRepository.save(hotel);
    }

    @SneakyThrows
    public Hotel removeById(Long id) throws IllegalArgumentException {
        return hotelRepository.removeById(id);
    }

    @SneakyThrows
    public Hotel findById(Long id) throws IllegalArgumentException {
        return hotelRepository.findById(id);
    }

    @SneakyThrows
    public Hotel update(Hotel hotel) throws IllegalArgumentException {
        return hotelRepository.update(hotel);
    }

    @SneakyThrows
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Iterable<Hotel> saveAll(Hotel... items) {
        return hotelRepository.saveAll(items);
    }

    public Iterable<Hotel> removeAllById(Long... ids) {
        return hotelRepository.removeAllById(ids);
    }

    public Iterable<Hotel> findAllById(Long... ids) {
        return hotelRepository.findAllById(ids);
    }

    public Iterable<Hotel> updateAll(Hotel... items) {
        return hotelRepository.updateAll(items);
    }
}
