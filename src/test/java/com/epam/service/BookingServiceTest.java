package com.epam.service;

import com.epam.domain.Booking;
import com.epam.repo.BookingRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class BookingServiceTest {

    private BookingService bookingService;

    @Mock
    private BookingRepositoryImpl repository;

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

        Booking after = bookingService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {

    }

    @Test
    public void findAll() {
    }

    @Test
    public void saveAll() {
    }

    @Test
    public void removeAllById() {
    }

    @Test
    public void findAllById() {
    }

    @Test
    public void updateAll() {
    }
}