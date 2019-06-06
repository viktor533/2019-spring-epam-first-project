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
    private Repository<Room, Long> repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roomService = new RoomService(repository);
    }

    private Room getDemoRoom() {
        return Room.builder().id(1).hotelId(1).numOfGuests(3).pricePerNight(2000)
                .roomClass(RoomClass.STANDART).build();
    }

    @Test
    public void save() {
        Room before = getDemoRoom();

        when(repository.save(before)).thenReturn(before);

        Room after = roomService.save(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeById() {
        Room before = getDemoRoom();

        when(repository.removeById(1L)).thenReturn(before);

        Room after = roomService.removeById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findById() {
        Room before = getDemoRoom();

        when(repository.findById(1L)).thenReturn(before);

        Room after = roomService.findById(1L);

        Assert.assertEquals(before, after);
    }

    @Test
    public void update() {
        Room before = getDemoRoom();

        when(repository.update(before)).thenReturn(before);

        Room after = roomService.update(before);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAll() {
        Iterable<Room> before = new ArrayList<>();

        when(repository.findAll()).thenReturn(before);

        Iterable<Room> after = roomService.findAll();

        Assert.assertEquals(before, after);
    }

    @Test
    public void saveAll() {
        Iterable<Room> before = new ArrayList<>();
        Room[] rooms = new Room[0];

        when(repository.saveAll(rooms)).thenReturn(before);

        Iterable<Room> after = roomService.saveAll(rooms);

        Assert.assertEquals(before, after);
    }

    @Test
    public void removeAllById() {
        Iterable<Room> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.removeAllById(ids)).thenReturn(before);

        Iterable<Room> after = roomService.removeAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void findAllById() {
        Iterable<Room> before = new ArrayList<>();
        Long[] ids = new Long[0];

        when(repository.findAllById(ids)).thenReturn(before);

        Iterable<Room> after = roomService.findAllById(ids);

        Assert.assertEquals(before, after);
    }

    @Test
    public void updateAll() {
        Iterable<Room> before = new ArrayList<>();
        Room[] rooms = new Room[0];

        when(repository.updateAll(rooms)).thenReturn(before);

        Iterable<Room> after = roomService.updateAll(rooms);

        Assert.assertEquals(before, after);
    }


}
