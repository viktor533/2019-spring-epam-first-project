package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.repo.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;


import java.util.ArrayList;

public class RoomServiceTest {
    private RoomService roomService;

    @Mock
    private Repository<Room, Long> roomRepository;
    @Mock
    private Repository<Hotel, Long> hotelRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roomService = new RoomService(roomRepository, hotelRepository);
    }

    private Hotel getHotel(int id) {
        return Hotel.builder().id(id).name("Dolphin").location("New York").luxury(4).build();
    }

    private Room getDemoRoom() {
        return Room.builder().id(1).hotelId(1).numOfGuests(3).pricePerNight(2000)
                .roomClass(RoomClass.STANDART).build();
    }

    @Test
    public void save() {
        Room before = getDemoRoom();

        Long id=1L;
        when(roomRepository.save(before)).thenReturn(before);
        when(hotelRepository.findById(id)).thenReturn(getHotel(id.intValue()));

        Room after = roomService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Room before = getDemoRoom();

        when(roomRepository.removeById(1L)).thenReturn(before);

        Room after = roomService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Room before = getDemoRoom();

        when(roomRepository.findById(1L)).thenReturn(before);

        Room after = roomService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Room before = getDemoRoom();

        Long id=1L;
        when(roomRepository.update(before)).thenReturn(before);
        when(roomRepository.findById(id)).thenReturn(before);
        when(hotelRepository.findById(id)).thenReturn(getHotel(id.intValue()));

        Room after = roomService.update(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAll() {
        Iterable<Room> before = new ArrayList<>();

        when(roomRepository.findAll()).thenReturn(before);

        Iterable<Room> after = roomService.findAll();

        Assert.assertEquals(before, after);
    }

    @Test
    public void saveAll() {
        Iterable<Room> before = new ArrayList<>();
        Room[] rooms = new Room[0];

        when(roomRepository.saveAll(rooms)).thenReturn(before);

        Iterable<Room> after = roomService.saveAll(rooms);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeAllById() {
        Iterable<Room> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(roomRepository.removeAllById(ids)).thenReturn(before);

        Iterable<Room> after = roomService.removeAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAllById() {
        Iterable<Room> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(roomRepository.findAllById(ids)).thenReturn(before);

        Iterable<Room> after = roomService.findAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void updateAll() {
        Iterable<Room> before = new ArrayList<>();
        Room[] rooms = new Room[0];

        when(roomRepository.updateAll(rooms)).thenReturn(before);

        Iterable<Room> after = roomService.updateAll(rooms);

        Assert.assertEquals(before, after);
    }


}
