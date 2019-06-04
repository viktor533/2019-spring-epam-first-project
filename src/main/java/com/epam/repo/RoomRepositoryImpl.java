package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoomRepositoryImpl implements Repository<Room, Long> {

    private static final String saveSQLRequest = "INSERT INTO ROOM (ID, HOTEL_ID, NUM_OF_GUESTS, PRICE_PER_NIGHT, CLASS) VALUES(?, ?, ?, ?, ?);";
    private static final String deleteSQLRequest = "DELETE FROM ROOM WHERE ID = ?;";
    private static final String findSQLRequest = "SELECT * FROM HOTEL WHERE ID = ?;";
    private static final String updateSQLRequest = "UPDATE ROOM SET ID \'?\', HOTEL_ID \'?\', NUM_OF_GUESTS = \'?\', PRICE_PER_NIGHT \'?\', CLASS = \'?\' WHERE ID = ?;";
    private static final String findAllSQLRequest = "SELECT * FROM HOTEL;";

    private static final String hotelIdColumnName = "HOTEL_ID";
    private static final String numOfGuestsColumnName = "NUM_OF_GUESTS";
    private static final String pricePerNightColumnName = "PRICE_PER_NIGHT";
    private static final String classColumnName = "CLASS";

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @Override
    @SneakyThrows
    public Room save(Room room) {
        if (room == null) {
            return null;
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(saveSQLRequest);
        setRoomToPreparedStatement(room, statement);
        statement.execute();

//        TODO: bookingRepositoryImp
//        for (Booking booking : room.getBookings()) {
//            bookingRepositoryImp.removeById(booking.getId());
//        }
        return room;
    }

    @Override
    @SneakyThrows
    public Room removeById(Long id) {
        if (id == null) {
            return null;
        }
        Room room = findById(id);
        if (room == null) {
            return null;
        }

//        TODO: bookingRepositoryImp
//        for (Booking booking : room.getBookings()) {
//            bookingRepositoryImp.removeById(booking.getId());
//        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(deleteSQLRequest);
        statement.setLong(1, id);
        statement.execute();

        return room;
    }

    @Override
    @SneakyThrows
    public Room findById(Long id) {
        if (id == null) {
            return null;
        }

        Room room = null;

        @Cleanup
        PreparedStatement statement = getPreparedStatement(findSQLRequest);

        try {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room = Room.builder()
                        .hotelId(resultSet.getLong(hotelIdColumnName))
                        .numOfGuests(resultSet.getInt(numOfGuestsColumnName))
                        .pricePerNight(resultSet.getInt(pricePerNightColumnName))
                        .roomClass(RoomClass.valueOf(resultSet.getString(classColumnName)))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
        if (room != null) {
            List<Booking> bookings = null;
//            TODO: bookingRepositoryImp
//            for (Booking booking : bookingRepositoryImp.findAll()) {
//                if (booking.getRoomId() == id) {
//                    bookings.add(booking);
//                }
//              bookingRepositoryImp.removeById(booking.getId());
//            }
            room.setBookings(bookings);
        }

        return room;
    }

    @Override
    @SneakyThrows
    public Room update(Room room) {
        if (room == null) {
            return null;
        }

        @Cleanup
        PreparedStatement statement = getPreparedStatement(updateSQLRequest);
        setRoomToPreparedStatement(room, statement);
        statement.setLong(6, room.getId());
        statement.execute();

//        TODO: bookingRepositoryImp
//        for (Booking booking : room.getBookings()) {
//            bookingRepositoryImp.update(booking);
//        }

        return room;
    }

    @SneakyThrows
    private static void setRoomToPreparedStatement(Room room, PreparedStatement statement) {
        statement.setLong(1, room.getId());
        statement.setLong(2, room.getHotelId());
        statement.setInt(3, room.getNumOfGuests());
        statement.setInt(4, room.getPricePerNight());
        statement.setString(5, room.getRoomClass().toString());
    }

    @Override
    @SneakyThrows
    public Iterable<Room> findAll() {
        @Cleanup
        PreparedStatement statement = getPreparedStatement(findAllSQLRequest);
        ResultSet resultSet = statement.executeQuery();
        List<Room> roomsList = new ArrayList<>();
        while (resultSet.next()) {
            Room room = Room.builder()
                    .hotelId(resultSet.getLong(hotelIdColumnName))
                    .numOfGuests(resultSet.getInt(numOfGuestsColumnName))
                    .pricePerNight(resultSet.getInt(pricePerNightColumnName))
                    .roomClass(RoomClass.valueOf(resultSet.getString(classColumnName)))
                    .build();

            List<Booking> bookings = null;
//            TODO: bookingRepositoryImp
//            for (Booking booking : bookingRepositoryImp.findAll()) {
//                if (booking.getRoomId() == id) {
//                    bookings.add(booking);
//                }
//              bookingRepositoryImp.removeById(booking.getId());
//            }
            room.setBookings(bookings);
            roomsList.add(room);
        }
        return roomsList;
    }
}
