package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.repo.Repository;
import com.epam.state.RepositoryState;

import java.util.Collections;

public class RoomServiceImpl {
    private Repository<Room, Long> roomRepository = RepositoryState.getRoomRepositoryInstance();
    private Repository<Hotel, Long> hotelRepository = RepositoryState.getHotelRepositoryInstance();

    /**
     * Check room and room fields by null
     * @param room to check
     * @throws IllegalArgumentException if accepted room or room fields is null
     */
    private void checkNulls(Room room) throws IllegalArgumentException {
        if (room == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }
        if (findById(room.getId()) != null) {
            throw new IllegalArgumentException("Room with same id already exist!");
        }
        if (hotelRepository.findById(room.getHotelId()) == null) {
            throw new IllegalArgumentException("Hotel id not correct: not exist!");
        }
        if (room.getRoomClass() == null) {
            throw new IllegalArgumentException("Room must have class!");
        }
        if (room.getBills() == null) {
            room.setBills(Collections.EMPTY_LIST);
        }
    }

    /**
     * Accept room, check by null and save it in repository
     * @param room
     * @return saved room
     * @throws IllegalArgumentException if accepted room or room fields is null
     */
    public Room save(Room room) throws IllegalArgumentException {
        checkNulls(room);
        return roomRepository.save(room);
    }

    /**
     * Accepted id and try to delete room with same id
     * @param id
     * @return removed room or null, if room with same id not exist
     * @throws IllegalArgumentException if id is null
     */
    public Room removeById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        return roomRepository.removeById(id);
    }

    /**
     * Accept id and try to find room with same id
     * @param id
     * @return finded room or null, if room with same id not exist
     * @throws IllegalArgumentException if id is null
     */
    public Room findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        return roomRepository.findById(id);
    }

    /**
     * Try to update room
     * @param room
     * @return updated room or null if same room not exist in repository
     * @throws IllegalArgumentException if accepted room or room fields is null
     */
    public Room update(Room room) throws IllegalArgumentException {
        checkNulls(room);
        if (findById(room.getId()) == null) {
            return null;
        }
        return roomRepository.update(room);
    }

    /**
     * @return Iterable by rooms, contained in repository
     */
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }
}
