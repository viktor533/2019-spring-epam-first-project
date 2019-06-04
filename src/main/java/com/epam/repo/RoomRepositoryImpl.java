package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.domain.Room;
import com.epam.domain.enums.RoomClass;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoomRepositoryImpl implements Repository<Room, Long> {

    @SneakyThrows
    private Statement getStatement() {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.createStatement();
    }

    @Override
    @SneakyThrows
    public Room save(Room room) {
        if (room == null) {
            return null;
        }

        long id = room.getId();
        long hotelId = room.getHotelId();
        int numOfGuests = room.getNumOfGuests();
        int pricePerNight = room.getPricePerNight();
        String roomClass = room.getRoomClass().toString();

        @Cleanup
        Statement statement = getStatement();
        statement.execute("INSERT INTO ROOM (ID, HOTEL_ID, NUM_OF_GUESTS, PRICE_PER_NIGHT, CLASS) VALUES (" +
                id + ", " + hotelId + ", " + numOfGuests + ", " + pricePerNight + ", " + roomClass + ");");

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
        Statement statement = getStatement();
        statement.execute("DELETE FROM ROOM WHERE ID = " + id + ";");

        return room;
    }

    @Override
    public Room findById(Long id) {
        if (id == null) {
            return null;
        }
        @Cleanup
        Statement statement = getStatement();
        Room room = null;

/*
        .roomId(roomId)
                .start(start)
                .end(end)
                .build();
*/

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM HOTEL WHERE ID = " + id + ";");
            if (resultSet.next()) {
                room = Room.builder()
                        .hotelId(resultSet.getLong("HOTEL_ID"))
                        .numOfGuests(resultSet.getInt("NUM_OF_GUESTS"))
                        .pricePerNight(resultSet.getInt("NUM_OF_GUESTS"))
                        .roomClass(RoomClass.valueOf(resultSet.getString("CLASS")))
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

        long id = room.getId();
        long hotelId = room.getHotelId();
        int numOfGuests = room.getNumOfGuests();
        int pricePerNight = room.getPricePerNight();
        String roomClass = room.getRoomClass().toString();

        @Cleanup
        Statement statement = getStatement();
        statement.execute("UPDATE ROOM SET ID \'" + id + "\', HOTEL_ID \'" + hotelId + "\', NUM_OF_GUESTS = \'" + numOfGuests + "\', " +
                "PRICE_PER_NIGHT \'" + pricePerNight + "\', CLASS = \'" + roomClass + "\' WHERE ID = " + id + ";");

//        TODO: bookingRepositoryImp
//        for (Booking booking : room.getBookings()) {
//            bookingRepositoryImp.update(booking);
//        }

        return room;
    }

    @Override
    @SneakyThrows
    public Iterable<Room> findAll() {
        @Cleanup
        Statement statement = getStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM HOTEL;");
        List<Room> roomsList = new ArrayList<>();
        while (resultSet.next()) {
            Room room = Room.builder()
                    .hotelId(resultSet.getLong("HOTEL_ID"))
                    .numOfGuests(resultSet.getInt("NUM_OF_GUESTS"))
                    .pricePerNight(resultSet.getInt("NUM_OF_GUESTS"))
                    .roomClass(RoomClass.valueOf(resultSet.getString("CLASS")))
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
