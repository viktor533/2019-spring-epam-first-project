package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.repo.Repository;
import com.epam.state.RepositoryState;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class RoomService {
    private Repository<Room, Long> roomRepository = RepositoryState.getRoomRepositoryInstance();
    private Repository<Hotel, Long> hotelRepository = RepositoryState.getHotelRepositoryInstance();

    public RoomService(Repository<Room, Long> roomRepository, Repository<Hotel, Long> hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    /**
     * Check room and room fields by null
     * @param room to check
     * @throws IllegalArgumentException if accepted room or room fields is null
     */
    private void checkNulls(Room room) throws IllegalArgumentException {
        if (room == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }
        if (hotelRepository.findById(room.getHotelId()) == null) {
            throw new IllegalArgumentException("Hotel id not correct: not exist!");
        }
        if (room.getRoomClass() == null) {
            throw new IllegalArgumentException("Room must have class!");
        }
        if (room.getBookings() == null) {
            room.setBookings(Collections.EMPTY_LIST);
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
        if (findById(room.getId()) != null) {
            return null;
        }
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

    /**
     * Saves the array of rooms into the database
     * @param rooms that we want to save in the database
     * @return Iterable of all Room objects that have been saved in the database
     */
    public Iterable<Room> saveAll(Room... rooms) {
        return Arrays.stream(rooms)
                .map(this::save)
                .collect(Collectors.toList());
//        return roomRepository.saveAll(rooms);
    }

    /**
     * Removes all rooms from the database by their ids
     * @param ids of the rooms that want to remove from the database
     * @return Iterable of all Room objects that have been removed from the database by their ids
     */
    public Iterable<Room> removeAllById(Long... ids) {
        return Arrays.stream(ids)
                .map(this::removeById)
                .collect(Collectors.toList());
//        return roomRepository.removeAllById(ids);
    }

    /**
     * Finds all rooms from the database by their ids
     * @param ids of the rooms that want to find in the database
     * @return Iterable of all Room objects by their ids
     */
    public Iterable<Room> findAllById(Long... ids) {
        return Arrays.stream(ids)
                .map(this::findById)
                .collect(Collectors.toList());
    }

    /**
     * Updates all rooms that have been passed into the method in the database
     * @param rooms that we want to update in the database
     * @return Iterable of all Room objects that have been updated in the database
     */
    public Iterable<Room> updateAll(Room... rooms) {
        return Arrays.stream(rooms)
                .map(this::update)
                .collect(Collectors.toList());
    }
}
