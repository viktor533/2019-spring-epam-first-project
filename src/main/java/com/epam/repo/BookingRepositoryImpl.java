package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.utils.DBConnectionUtils;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class BookingRepositoryImpl implements Repository<Booking, Long> {
    private long id;
    private long roomId;
    private LocalDate start;
    private LocalDate end;

    @Override
    @SneakyThrows
    public Booking save(Booking booking) {
        if (booking != null) {
            Statement statement = getStatement();

            id = booking.getId();
            roomId = booking.getRoomId();
            start = booking.getStart();
            end = booking.getEnd();

            statement.execute("INSERT INTO BOOKING (ID, ROOM_ID, START_DATE, END_DATE) VALUES (" +
                    id + ", " + roomId + ", " + start + ", " + end + ")");

            return booking;
        } else {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public Booking removeById(Long id) {
        if (id != null) {
            Booking booking = findById(id);

            Statement statement = getStatement();
            statement.execute("DELETE FROM BOOKING WHERE ID = " + id);

            return booking;
        } else {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public Booking findById(Long id) {
        if (id != null) {
            Statement statement = getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKING WHERE ID = " + id);

            while (resultSet.next()) {
                start = LocalDate.parse(resultSet.getString("START_DATE"));
                end = LocalDate.parse(resultSet.getString("END_DATE"));
                roomId = resultSet.getInt("ROOM_ID");
            }

            Booking booking = Booking.builder().build();

            booking.setRoomId(roomId);
            booking.setStart(start);
            booking.setEnd(end);

            return booking;
        } else {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public Booking update(Booking booking) {
        if (booking != null) {
            Statement statement = getStatement();

            id = booking.getId();
            roomId = booking.getRoomId();
            start = booking.getStart();
            end = booking.getEnd();

            statement.execute("UPDATE BOOKING SET ROOM_ID \'" + roomId + "\', START_DATE \'" + start + "\', END_DATE = \'" + end + "\' WHERE ID = " + id);

            return booking;
        } else {
            return null;
        }
    }

    @Override
    public Iterable<Booking> findAll() {
        return null;
    }

    @SneakyThrows
    private Statement getStatement() {
        Connection connection = DBConnectionUtils.getConnection();
        Statement statement = connection.createStatement();
        return statement;
    }
}
