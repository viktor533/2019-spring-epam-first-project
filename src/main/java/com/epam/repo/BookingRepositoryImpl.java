package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.domain.enums.RoomClass;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements Repository<Booking, Long> {
    private final String SAVE_SQL_REQUEST = "INSERT INTO BOOKING (CLASS, START_DATE, END_DATE) VALUES (?, ?, ?)";
    private final String DELETE_SQL_REQUEST = "DELETE FROM BOOKING WHERE ID = ?";
    private final String FIND_SQL_REQUEST = "SELECT * FROM BOOKING WHERE ID = ?";
    private final String UPDATE_SQL_REQUEST = "UPDATE BOOKING SET CLASS = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?";
    private final String FIND_ALL_SQL_REQUEST = "SELECT * FROM BOOKING";

    private final String ID_COLUMN_NAME = "ID";
    private final String ROOM_CLASS_COLUMN_NAME = "CLASS";
    private final String START_DATE_COLUMN_NAME = "START_DATE";
    private final String END_DATE_COLUMN_NAME = "END_DATE";

    private final String SAVE_EXCEPTION_MESSAGE = "Passing empty booking field to save";
    private final String REMOVE_EXCEPTION_MESSAGE = "Passing empty id field to remove";
    private final String FIND_EXCEPTION_MESSAGE = "Passing empty id field to find";
    private final String UPDATE_EXCEPTION_MESSAGE = "Passing empty booking field to update";

    @Override
    @SneakyThrows
    public Booking save(Booking booking) throws IllegalArgumentException {
        if (booking == null) {
            throw new IllegalArgumentException(SAVE_EXCEPTION_MESSAGE);
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement(SAVE_SQL_REQUEST);

            statement.setString(1, booking.getRoomClass().toString());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());
            statement.executeUpdate();

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking removeById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException(REMOVE_EXCEPTION_MESSAGE);
        } else {
            Booking booking = findById(id);

            @Cleanup
            PreparedStatement statement = getPreparedStatement(DELETE_SQL_REQUEST);
            statement.setLong(1, id);
            statement.executeUpdate();

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException(FIND_EXCEPTION_MESSAGE);
        } else {
            Booking booking = null;

            @Cleanup
            PreparedStatement statement = getPreparedStatement(FIND_SQL_REQUEST);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RoomClass roomClass = RoomClass.valueOf(resultSet.getString(ROOM_CLASS_COLUMN_NAME));
                LocalDate start = LocalDate.parse(resultSet.getString(START_DATE_COLUMN_NAME));
                LocalDate end = LocalDate.parse(resultSet.getString(END_DATE_COLUMN_NAME));
                booking = Booking.builder().id(id).roomClass(roomClass).start(start).end(end).build();
            }

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking update(Booking booking) throws IllegalArgumentException {
        if (booking == null) {
            throw new IllegalArgumentException(UPDATE_EXCEPTION_MESSAGE);
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement(UPDATE_SQL_REQUEST);

            statement.setString(1, booking.getRoomClass().toString());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());
            statement.setLong(4, booking.getId());
            statement.executeUpdate();

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking;

        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_ALL_SQL_REQUEST);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            booking = Booking.builder().id(resultSet.getLong(ID_COLUMN_NAME))
                             .roomClass(RoomClass.valueOf(resultSet.getString(ROOM_CLASS_COLUMN_NAME)))
                             .start(LocalDate.parse(resultSet.getString(START_DATE_COLUMN_NAME)))
                             .end(LocalDate.parse(resultSet.getString(END_DATE_COLUMN_NAME)))
                             .build();

            bookingList.add(booking);
        }

        return bookingList;

    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }
}
