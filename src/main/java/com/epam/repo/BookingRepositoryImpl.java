package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements Repository<Booking, Long> {
<<<<<<< HEAD
    private final String SAVE_SQL_REQUEST = "INSERT INTO BOOKING (ROOM_ID, START_DATE, END_DATE) VALUES (?, ?, ?)";
    private final String DELETE_SQL_REQUEST = "DELETE FROM BOOKING WHERE ID = ?";
    private final String FIND_SQL_REQUEST = "SELECT * FROM BOOKING WHERE ID = ?";
    private final String UPDATE_SQL_REQUEST = "UPDATE BOOKING SET ROOM_ID = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?";
    private final String FIND_ALL_SQL_REQUEST = "SELECT * FROM BOOKING";
    private final String ID_COLUMN_NAME = "ID";
    private final String ROOM_ID_COLUMN_NAME = "ROOM_ID";
    private final String START_DATE_COLUMN_NAME = "START_DATE";
    private final String END_DATE_COLUMN_NAME = "END_DATE";
=======
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

    @Override
    @SneakyThrows
    public Booking save(Booking booking) {
        if (booking == null) {
            return null;
        } else {
            @Cleanup
<<<<<<< HEAD
            PreparedStatement statement = getPreparedStatement(SAVE_SQL_REQUEST);
=======
            PreparedStatement statement = getPreparedStatement("INSERT INTO BOOKING (ROOM_ID, START_DATE, END_DATE) VALUES (?, ?, ?)");
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

            statement.setLong(1, booking.getRoomId());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());
<<<<<<< HEAD
            statement.executeUpdate();
=======
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

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
<<<<<<< HEAD
            PreparedStatement statement = getPreparedStatement(DELETE_SQL_REQUEST);
=======
            PreparedStatement statement = getPreparedStatement("DELETE FROM BOOKING WHERE ID = ?");
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
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
<<<<<<< HEAD
            PreparedStatement statement = getPreparedStatement(FIND_SQL_REQUEST);
=======
            PreparedStatement statement = getPreparedStatement("SELECT * FROM BOOKING WHERE ID = ?");
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
<<<<<<< HEAD
                long roomId = resultSet.getInt(ROOM_ID_COLUMN_NAME);
                LocalDate start = LocalDate.parse(resultSet.getString(START_DATE_COLUMN_NAME));
                LocalDate end = LocalDate.parse(resultSet.getString(END_DATE_COLUMN_NAME));
=======
                long roomId = resultSet.getInt("ROOM_ID");
                LocalDate start = LocalDate.parse(resultSet.getString("START_DATE"));
                LocalDate end = LocalDate.parse(resultSet.getString("END_DATE"));
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

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
<<<<<<< HEAD
            PreparedStatement statement = getPreparedStatement(UPDATE_SQL_REQUEST);

            statement.setLong(1, booking.getRoomId());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());
            statement.setLong(4, booking.getId());
            statement.executeUpdate();
=======
            PreparedStatement statement = getPreparedStatement("UPDATE BOOKING SET ROOM_ID = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?");
            statement.setLong(1, booking.getRoomId());
            statement.setString(2, booking.getStart().toString());
            statement.setString(3, booking.getEnd().toString());
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2

            return booking;
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking;

        @Cleanup
<<<<<<< HEAD
        PreparedStatement statement = getPreparedStatement(FIND_ALL_SQL_REQUEST);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            booking = Booking.builder().id(resultSet.getLong(ID_COLUMN_NAME))
                             .roomId(resultSet.getLong(ROOM_ID_COLUMN_NAME))
                             .start(LocalDate.parse(resultSet.getString(START_DATE_COLUMN_NAME)))
                             .end(LocalDate.parse(resultSet.getString(END_DATE_COLUMN_NAME)))
=======
        Statement statement = getPreparedStatement("SELECT * FROM BOOKING");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            booking = Booking.builder().id(resultSet.getLong("ID"))
                             .roomId(resultSet.getLong("ROOM_ID"))
                             .start(LocalDate.parse(resultSet.getString("START_DATE")))
                             .end(LocalDate.parse(resultSet.getString("END_DATE")))
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
                             .build();

            bookingList.add(booking);
        }

<<<<<<< HEAD
        return bookingList;
=======
        return null;
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
<<<<<<< HEAD
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
=======
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }

    @SneakyThrows
    private Statement getStatement() {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.createStatement();
>>>>>>> d060a7547afc499c83a982251197a57dac088ac2
    }
}
