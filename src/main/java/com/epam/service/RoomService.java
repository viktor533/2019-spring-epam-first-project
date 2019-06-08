package com.epam.service;

import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.repo.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class RoomService {
    private Repository<Room, Long> roomRepository;
    private Repository<Hotel, Long> hotelRepository;

    public RoomService(Repository<Room, Long> roomRepository, Repository<Hotel, Long> hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    /**
     * Check room and room fields by null
     * @param room to check
     */
    private void checkNulls(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }
        if (hotelRepository.findById(room.getHotelId()) == null) {
            throw new IllegalArgumentException("Hotel id not correct: not exist!");
        }
        if (room.getRoomClass() == null) {
            throw new IllegalArgumentException("Room must have class!");
        }
        if (room.getBills() == null) {
            room.setBills(Collections.emptyList());
        }
    }

    /**
     * Accept room, check by null and save it in repository
     * @param room - is room we need to save
     * @return saved room
     */
    public Room save(Room room) {
        checkNulls(room);
        if (findById(room.getId()) != null) {
            return null;
        }
        return roomRepository.save(room);
    }

    /**
     * Accepted id and try to delete room with same id
     * @param id - identifier that we need to delete by
     * @return removed room or null, if room with same id not exist
     */
    public Room removeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        return roomRepository.removeById(id);
    }

    /**
     * Accept id and try to find room with same id
     * @param id - identifier that we need to get by
     * @return finded room or null, if room with same id not exist
     */
    public Room findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        return roomRepository.findById(id);
    }

    /**
     * Try to update room
     * @param room - the object that we need to update
     * @return updated room or null if same room not exist in repository
     */
    public Room update(Room room) {
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
