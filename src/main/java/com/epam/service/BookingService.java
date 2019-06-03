package com.epam.service;

import com.epam.domain.Booking;
import com.epam.repo.BookingRepositoryImpl;
import lombok.SneakyThrows;

public class BookingService {
    @SneakyThrows
    public Booking save(Booking booking) throws IllegalArgumentException {
        return bookingRepository.save(booking);
    }

    @SneakyThrows
    public Booking removeById(Long id) throws IllegalArgumentException {
        return bookingRepository.removeById(id);
    }

    @SneakyThrows
    public Booking findById(Long id) throws IllegalArgumentException {
        return bookingRepository.findById(id);
    }

    @SneakyThrows
    public Booking update(Booking booking) throws IllegalArgumentException {
        return bookingRepository.update(booking);
    }

    @SneakyThrows
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Iterable<Booking> saveAll(Booking... items) {
        return bookingRepository.saveAll(items);
    }

    public Iterable<Booking> removeAllById(Long... ids) {
        return bookingRepository.removeAllById(ids);
    }

    public Iterable<Booking> findAllById(Long... ids) {
        return bookingRepository.findAllById(ids);
    }

    public Iterable<Booking> updateAll(Booking... items) {
        return bookingRepository.updateAll(items);
    }

    BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();
}
