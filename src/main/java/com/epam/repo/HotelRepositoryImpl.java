package com.epam.repo;

import com.epam.domain.Booking;
import com.epam.domain.Hotel;
import com.epam.domain.Room;
import com.epam.utils.DBConnectionUtils;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.domain.Hotel.builder;

public class HotelRepositoryImpl implements Repository<Hotel, Long> {
//todo    Repository <Room, Long> roomRepository = new RoomRepositoryImpl();

    private final String SAVE_SQL_REQUEST = "INSERT INTO HOTEL (NAME, LOCATION, LUXURY) VALUES (?, ?, ?)";
    private final String DELETE_SQL_REQUEST = "DELETE FROM HOTEL WHERE ID = ?";
    private final String FIND_SQL_REQUEST = "SELECT * FROM HOTEL WHERE ID = ?";
    private final String UPDATE_SQL_REQUEST = "UPDATE HOTEL SET NAME = ?, LOCATION = ?, LUXURY = ? WHERE ID = ?";
    private final String FIND_ALL_SQL_REQUEST = "SELECT * FROM HOTEL";
    private final String ID_COLUMN_NAME = "ID";
    private final String NAME_COLUMN_NAME = "NAME";
    private final String LOCATION_DATE_COLUMN_NAME = "LOCATION";
    private final String LUXURY_COLUMN_NAME = "LUXURY";

    @Override
    @SneakyThrows
    public Hotel save(Hotel hotel) {
        if (hotel == null) {
            return null;
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement(SAVE_SQL_REQUEST);
            List<Room> roomList = hotel.getRooms();
            for (Room room : roomList) {
//todo                roomRepository.save(room);
            }

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setInt(3, hotel.getLuxury());
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel removeById(Long id) {
        if (id == null) {
            return null;
        } else {
            Hotel hotel = findById(id);

            @Cleanup
            PreparedStatement statement = getPreparedStatement(DELETE_SQL_REQUEST);
            statement.setLong(1, id);
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel findById(Long id) {
        if (id == null) {
            return null;
        } else {
            Hotel hotel = null;

            @Cleanup
            PreparedStatement statement = getPreparedStatement(FIND_SQL_REQUEST);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(NAME_COLUMN_NAME);
                String location = resultSet.getString(LOCATION_DATE_COLUMN_NAME);
                int luxury = resultSet.getInt(LUXURY_COLUMN_NAME);

                hotel = Hotel.builder().id(id).name(name).location(location).luxury(luxury).build();
            }

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Hotel update(Hotel hotel) {
        if (hotel == null) {
            return null;
        } else {
            @Cleanup
            PreparedStatement statement = getPreparedStatement(UPDATE_SQL_REQUEST);

            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getLocation());
            statement.setInt(3, hotel.getLuxury());
            statement.setLong(4, hotel.getId());
            statement.executeUpdate();

            return hotel;
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Hotel> findAll() {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel;

        @Cleanup
        PreparedStatement statement = getPreparedStatement(FIND_ALL_SQL_REQUEST);
        ResultSet resultSet = statement.executeQuery();

//todo        while (resultSet.next()) {
//            hotel = Hotel.builder().id(resultSet.getLong(ID_COLUMN_NAME))
//                                    .name(resultSet.getLong(ROOM_ID_COLUMN_NAME))
//                                    .start(LocalDate.parse(resultSet.getString(START_DATE_COLUMN_NAME)))
//                                    .end(LocalDate.parse(resultSet.getString(END_DATE_COLUMN_NAME)))
//                                    .build();
//
//            hotelList.add(room);
//        }

        return hotelList;
    }

    @SneakyThrows
    private PreparedStatement getPreparedStatement(String sql) {
        Connection connection = DBConnectionUtils.getConnection();
        return connection.prepareStatement(sql);
    }
}
