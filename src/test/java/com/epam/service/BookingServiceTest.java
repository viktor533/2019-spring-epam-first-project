package com.epam.service;

import com.epam.domain.Booking;
import com.epam.repo.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class BookingServiceTest {
    private BookingService bookingService;

    @Mock
    private Repository<Booking, Long> repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingService(repository);
    }


    @Test
    public void save() {
        Booking before = Booking.builder().id(1).roomId(2).start(LocalDate.parse("2019-04-05")).end(LocalDate.parse("2019-04-07")).build();

        when(repository.save(before)).thenReturn(before);

        Booking after = bookingService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Booking before = Booking.builder().id(1).roomId(2).start(LocalDate.parse("2019-04-05")).end(LocalDate.parse("2019-04-07")).build();

        when(repository.removeById(1L)).thenReturn(before);

        Booking after = bookingService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Booking before = Booking.builder().id(1).roomId(2).start(LocalDate.parse("2019-04-05")).end(LocalDate.parse("2019-04-07")).build();

        when(repository.findById(1L)).thenReturn(before);

        Booking after = bookingService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Booking before = Booking.builder().id(1).roomId(2).start(LocalDate.parse("2019-04-05")).end(LocalDate.parse("2019-04-07")).build();

        when(repository.update(before)).thenReturn(before);

        Booking after = bookingService.update(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAll() {
        Iterable<Booking> before = new ArrayList<>();

        when(repository.findAll()).thenReturn(before);

        Iterable<Booking> after = bookingService.findAll();

        Assert.assertEquals(before, after);
    }

    @Test
    public void saveAll() {
        Iterable<Booking> before = new ArrayList<>();
        Booking[] bookings = new Booking[0];

        when(repository.saveAll(bookings)).thenReturn(before);

        Iterable<Booking> after = bookingService.saveAll(bookings);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeAllById() {
        Iterable<Booking> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.removeAllById(ids)).thenReturn(before);

        Iterable<Booking> after = bookingService.removeAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAllById() {
        Iterable<Booking> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.findAllById(ids)).thenReturn(before);

        Iterable<Booking> after = bookingService.findAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void updateAll() {
        Iterable<Booking> before = new ArrayList<>();
        Booking[] bookings = new Booking[0];

        when(repository.updateAll(bookings)).thenReturn(before);

        Iterable<Booking> after = bookingService.updateAll(bookings);

        Assert.assertEquals(before, after);
    }
}