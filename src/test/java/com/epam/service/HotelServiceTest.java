package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.repo.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class HotelServiceTest {
    private HotelService hotelService;

    @Mock
    private Repository<Hotel, Long> repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        hotelService = new HotelService(repository);
    }

    @Test
    public void save() {
        Hotel before = Hotel.builder().id(1).name("Dolphin").location("New York").luxury(4).build();

        when(repository.save(before)).thenReturn(before);

        Hotel after = hotelService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Hotel before = Hotel.builder().id(1).name("Dolphin").location("New York").luxury(4).build();

        when(repository.removeById(1L)).thenReturn(before);

        Hotel after = hotelService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Hotel before = Hotel.builder().id(1).name("Dolphin").location("New York").luxury(4).build();

        when(repository.findById(1L)).thenReturn(before);

        Hotel after = hotelService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Hotel before = Hotel.builder().id(1).name("Dolphin").location("New York").luxury(4).build();

        when(repository.update(before)).thenReturn(before);

        Hotel after = hotelService.update(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAll() {
        Iterable<Hotel> before = new ArrayList<>();

        when(repository.findAll()).thenReturn(before);

        Iterable<Hotel> after = hotelService.findAll();

        Assert.assertEquals(before, after);
    }

    @Test
    public void saveAll() {
        Iterable<Hotel> before = new ArrayList<>();
        Hotel[] hotels = new Hotel[0];

        when(repository.saveAll(hotels)).thenReturn(before);

        Iterable<Hotel> after = hotelService.saveAll(hotels);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeAllById() {
        Iterable<Hotel> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.removeAllById(ids)).thenReturn(before);

        Iterable<Hotel> after = hotelService.removeAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAllById() {
        Iterable<Hotel> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.findAllById(ids)).thenReturn(before);

        Iterable<Hotel> after = hotelService.findAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void updateAll() {
        Iterable<Hotel> before = new ArrayList<>();
        Hotel[] hotels = new Hotel[0];

        when(repository.updateAll(hotels)).thenReturn(before);

        Iterable<Hotel> after = hotelService.updateAll(hotels);

        Assert.assertEquals(before, after);
    }
}