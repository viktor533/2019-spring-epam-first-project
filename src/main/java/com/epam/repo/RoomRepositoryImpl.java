package com.epam.repo;

import com.epam.domain.Bill;
import com.epam.domain.Booking;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.state.RepositoryState;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class RoomRepositoryImpl implements Repository<Room, Long> {

    private static final String SAVE_SQL_REQUEST = "INSERT INTO ROOM (ID, HOTEL_ID, NUMBER, NUM_OF_GUESTS, PRICE_PER_NIGHT, CLASS) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String DELETE_SQL_REQUEST = "DELETE FROM ROOM WHERE ID = ?;";
    private static final String FIND_SQL_REQUEST = "SELECT * FROM ROOM WHERE ID = ?;";
    private static final String UPDATE_SQL_REQUEST = "UPDATE ROOM SET ID \'?\', HOTEL_ID \'?\', NUMBER = \'?\', NUM_OF_GUESTS = \'?\', PRICE_PER_NIGHT \'?\', CLASS = \'?\' WHERE ID = ?;";
    private static final String FIND_ALL_SQL_REQUEST = "SELECT * FROM ROOM;";

    private static final String ID_COLUMN_NAME = "ID";
    private static final String HOTEL_ID_COLUMN_NAME = "HOTEL_ID";
    private static final String NUM_OF_GUESTS_COLUMN_NAME = "NUM_OF_GUESTS";
    private static final String PRICE_PER_NIGHT_COLUMN_NAME = "PRICE_PER_NIGHT";
    private static final String CLASS_COLUMN_NAME = "CLASS";
    private static final String NUMBER_COLUMN_NAME = "NUMBER";

    private Repository<Bill, Long> billRepository = RepositoryState.getBillRepositoryInstance();

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @Override
    @SneakyThrows
    public Room save(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(SAVE_SQL_REQUEST);
        setRoomToPreparedStatement(room, statement);
        statement.execute();

        for (Bill bill : room.getBills()) {
            billRepository.save(bill);
        }
        return room;
    }

    @Override
    @SneakyThrows
    public Room removeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }
        Room room = findById(id);
        if (room == null) {
            return null;
        }

        for (Bill bills : room.getBills()) {
            billRepository.removeById(bills.getId());
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(DELETE_SQL_REQUEST);
        statement.setLong(1, id);
        statement.execute();

        return room;
    }

    @Override
    @SneakyThrows
    public Room findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Accepted id is null!");
        }

        Room room = null;

        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_SQL_REQUEST);

        try {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room = Room.builder()
                        .id(resultSet.getLong(ID_COLUMN_NAME))
                        .hotelId(resultSet.getLong(HOTEL_ID_COLUMN_NAME))
                        .number(resultSet.getInt(NUMBER_COLUMN_NAME))
                        .numOfGuests(resultSet.getInt(NUM_OF_GUESTS_COLUMN_NAME))
                        .pricePerNight(resultSet.getInt(PRICE_PER_NIGHT_COLUMN_NAME))
                        .roomClass(RoomClass.valueOf(resultSet.getString(CLASS_COLUMN_NAME)))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
        if (room != null) {
            addBillsToRoom(room);
        }

        return room;
    }

    @Override
    @SneakyThrows
    public Room update(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Accepted room is null!");
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(UPDATE_SQL_REQUEST);
        setRoomToPreparedStatement(room, statement);
        statement.setLong(6, room.getId());
        statement.execute();

        for (Bill bill : room.getBills()) {
            billRepository.update(bill);
        }

        return room;
    }

    @Override
    @SneakyThrows
    public Iterable<Room> findAll() {
        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_ALL_SQL_REQUEST);
        ResultSet resultSet = statement.executeQuery();
        List<Room> roomsList = new ArrayList<>();
        while (resultSet.next()) {
            Room room = Room.builder()
                    .id(resultSet.getLong(ID_COLUMN_NAME))
                    .hotelId(resultSet.getLong(HOTEL_ID_COLUMN_NAME))
                    .number(resultSet.getInt(NUMBER_COLUMN_NAME))
                    .numOfGuests(resultSet.getInt(NUM_OF_GUESTS_COLUMN_NAME))
                    .pricePerNight(resultSet.getInt(PRICE_PER_NIGHT_COLUMN_NAME))
                    .roomClass(RoomClass.valueOf(resultSet.getString(CLASS_COLUMN_NAME)))
                    .build();

            addBillsToRoom(room);
            roomsList.add(room);
        }
        return roomsList;
    }

    private void addBillsToRoom(Room room) {
        List<Bill> bills = null;
        Iterable<Bill> allBookings =  billRepository.findAll();
        if (allBookings != null) {
            bills = new ArrayList<>();
            for (Bill bill : allBookings) {
                if (bill.getRoomId() == room.getId()) {
                    bills.add(bill);
                }
            }
        } else {
            bills = Collections.emptyList();
        }
        room.setBills(bills);
    }

    @SneakyThrows
    private static void setRoomToPreparedStatement(Room room, PreparedStatement statement) {
        statement.setLong(1, room.getId());
        statement.setLong(2, room.getHotelId());
        statement.setLong(3, room.getNumber());
        statement.setInt(4, room.getNumOfGuests());
        statement.setInt(5, room.getPricePerNight());
        statement.setString(6, room.getRoomClass().toString());
    }
}
