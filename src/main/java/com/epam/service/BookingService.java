package com.epam.service;

import com.epam.domain.Booking;
import com.epam.repo.BookingRepositoryImpl;
import lombok.SneakyThrows;

public class BookingService {
    BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();

    /**
     * Saves booking into the database
     *
     * @param booking
     * @return Booking object that has been saved into the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Booking save(Booking booking) throws IllegalArgumentException {
        return bookingRepository.save(booking);
    }

    /**
     * Removes Booking from the database by its id
     *
     * @param id
     * @return Booking object that has been removed from the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Booking removeById(Long id) throws IllegalArgumentException {
        return bookingRepository.removeById(id);
    }

    /**
     * Returns Booking from the database by its id
     *
     * @param id
     * @return Booking object from the database by its id
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Booking findById(Long id) throws IllegalArgumentException {
        return bookingRepository.findById(id);
    }

    /**
     * Updates Booking in the database
     *
     * @param booking
     * @return Booking object that has been updated in the database
     * @throws IllegalArgumentException
     */
    @SneakyThrows
    public Booking update(Booking booking) throws IllegalArgumentException {
        return bookingRepository.update(booking);
    }

    /**
     * Finds all Bookings from database
     *
     * @return Iterable of all Booking objects in the database
     */
    @SneakyThrows
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    /**
     * Saves the array of Bookings into the database
     *
     * @param items
     * @return Iterable of all Booking objects that have been saved in the database
     */
    public Iterable<Booking> saveAll(Booking... items) {
        return bookingRepository.saveAll(items);
    }

    /**
     * Removes all Bookings from the database by their ids
     *
     * @param ids
     * @return Iterable of all Booking objects that have been removed from the database by their ids
     */
    public Iterable<Booking> removeAllById(Long... ids) {
        return bookingRepository.removeAllById(ids);
    }

    /**
     * Finds all Bookings from the database by their ids
     *
     * @param ids
     * @return Iterable of all Booking objects by their ids
     */
    public Iterable<Booking> findAllById(Long... ids) {
        return bookingRepository.findAllById(ids);
    }

    /**
     * Updates all Bookings that have been passed into the method in the database
     *
     * @param bookings
     * @return Iterable of all Bookings that have been updated in the database
     */
    public Iterable<Booking> updateAll(Booking... bookings) {
        return bookingRepository.updateAll(bookings);
    }
}
