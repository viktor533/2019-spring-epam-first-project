package com.epam.service;

import com.epam.domain.Booking;
import com.epam.repo.BookingRepositoryImpl;
import com.epam.repo.Repository;
import lombok.SneakyThrows;

public class BookingService {
    private final Repository<Booking, Long> bookingRepository;

    public BookingService(Repository<Booking, Long> bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Saves booking into the database
     *
     * @param booking that we want to save in the database
     * @return Booking object that has been saved into the database
     */
    @SneakyThrows
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    /**
     * Removes booking from the database by its id
     *
     * @param id of the booking that want to delete from the database
     * @return Booking object that has been removed from the database
     */
    @SneakyThrows
    public Booking removeById(Long id) {
        return bookingRepository.removeById(id);
    }

    /**
     * Returns booking from the database by its id
     *
     * @param id of the booking that want to find from the database
     * @return Booking object from the database by its id
     */
    @SneakyThrows
    public Booking findById(Long id)  {
        return bookingRepository.findById(id);
    }

    /**
     * Updates booking in the database
     *
     * @param booking that we want to update in the database
     * @return Booking object that has been updated in the database
     */
    @SneakyThrows
    public Booking update(Booking booking)  {
        return bookingRepository.update(booking);
    }

    /**
     * Finds all bookings from the database
     *
     * @return Iterable of all Booking objects in the database
     */
    @SneakyThrows
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    /**
     * Saves the array of bookings into the database
     *
     * @param bookings that we want to save in the database
     * @return Iterable of all Booking objects that have been saved in the database
     */
    public Iterable<Booking> saveAll(Booking... bookings) {
        return bookingRepository.saveAll(bookings);
    }

    /**
     * Removes all bookings from the database by their ids
     *
     * @param ids of the bookings that want to remove from the database
     * @return Iterable of all Booking objects that have been removed from the database by their ids
     */
    public Iterable<Booking> removeAllById(Long... ids) {
        return bookingRepository.removeAllById(ids);
    }

    /**
     * Finds all bookings from the database by their ids
     *
     * @param ids of the bookings that want to find in the database
     * @return Iterable of all Booking objects by their ids
     */
    public Iterable<Booking> findAllById(Long... ids) {
        return bookingRepository.findAllById(ids);
    }

    /**
     * Updates all bookings that have been passed into the method in the database
     *
     * @param bookings that we want to update in the database
     * @return Iterable of all Booking objects that have been updated in the database
     */
    public Iterable<Booking> updateAll(Booking... bookings) {
        return bookingRepository.updateAll(bookings);
    }
}
