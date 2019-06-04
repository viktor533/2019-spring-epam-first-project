package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements Repository<Booking, Long> {

    @Override
    @SneakyThrows
    public Booking save(Booking booking) {
        if (booking == null) {
            return null;
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement("INSERT INTO BOOKING (ROOM_ID, START_DATE, END_DATE) VALUES (?, ?, ?)");

            statement.setLong(1, booking.getRoomId());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking removeById(Long id) {
        if (id == null) {
            return null;
        } else {
            Booking booking = findById(id);

            @Cleanup
            PreparedStatement statement = getPreparedStatement("DELETE FROM BOOKING WHERE ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking findById(Long id) {
        if (id == null) {
            return null;
        } else {
            Booking booking = null;

            @Cleanup
            PreparedStatement statement = getPreparedStatement("SELECT * FROM BOOKING WHERE ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long roomId = resultSet.getInt("ROOM_ID");
                LocalDate start = LocalDate.parse(resultSet.getString("START_DATE"));
                LocalDate end = LocalDate.parse(resultSet.getString("END_DATE"));

                booking = Booking.builder().id(id).roomId(roomId).start(start).end(end).build();
            }

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Booking update(Booking booking) {
        if (booking == null) {
            return null;
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement("UPDATE BOOKING SET ROOM_ID = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?");
            statement.setLong(1, booking.getRoomId());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking;

        @Cleanup
        Statement statement = getPreparedStatement("SELECT * FROM BOOKING");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            booking = Booking.builder().id(resultSet.getLong("ID"))
                             .roomId(resultSet.getLong("ROOM_ID"))
                             .start(LocalDate.parse(resultSet.getString("START_DATE")))
                             .end(LocalDate.parse(resultSet.getString("END_DATE")))
                             .build();

            bookingList.add(booking);
        }

        return null;
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @SneakyThrows
    private Statement getStatement() {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.createStatement();
    }
}
