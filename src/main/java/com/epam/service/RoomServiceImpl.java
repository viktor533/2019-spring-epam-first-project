package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.repo.Repository;
import com.epam.state.RepositoryState;

import java.util.Collections;

public class RoomServiceImpl {
    private Repository<Room, Long> roomRepository = RepositoryState.getRoomRepositoryInstance();
    private Repository<Hotel, Long> hotelRepository = RepositoryState.getHotelRepositoryInstance();

    private void checkNulls(Room room) throws Exception {
        if (findById(room.getId()) != null) {
            throw new Exception("Room with same id already exist!");
        }
        if (hotelRepository.findById(room.getHotelId()) == null) {
            throw new Exception("Hotel id not correct: not exist!");
        }
        if (room.getRoomClass() == null) {
            throw new Exception("Room must have class!");
        }
        if (room.getBookings() == null) {
            room.setBookings(Collections.EMPTY_LIST);
        }
    }

    public Room save(Room room) throws Exception {
        checkNulls(room);
        return roomRepository.save(room);
    }

    public Room removeById(Long id) {
        if (id == null) {
            return null;
        }
        return roomRepository.removeById(id);
    }

    public Room findById(Long id) {
        if (id == null) {
            return null;
        }
        return roomRepository.findById(id);
    }

    public Room update(Room room) throws Exception {
        if (findById(room.getId()) == null) {
            return null;
        }
        checkNulls(room);
        return roomRepository.update(room);
    }

    public Iterable<Room> findAll(Long id) {
        if (id == null) {
            return null;
        }
        return roomRepository.findAll();
    }
}
